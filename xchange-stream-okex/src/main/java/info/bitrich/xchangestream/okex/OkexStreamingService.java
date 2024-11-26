package info.bitrich.xchangestream.okex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import info.bitrich.xchangestream.okex.dto.OkexLoginMessage;
import info.bitrich.xchangestream.okex.dto.OkexSubscribeMessage;
import info.bitrich.xchangestream.okex.dto.OkexSubscribeMessage.SubscriptionTopic;
import info.bitrich.xchangestream.service.netty.JsonNettyStreamingService;
import info.bitrich.xchangestream.service.netty.WebSocketClientHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.okex.dto.OkexInstType;
import org.knowm.xchange.service.BaseParamsDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkexStreamingService extends JsonNettyStreamingService {

  private static final Logger LOG = LoggerFactory.getLogger(OkexStreamingService.class);
  private static final String LOGIN_SIGN_METHOD = "GET";
  private static final String LOGIN_SIGN_REQUEST_PATH = "/users/self/verify";

  private static final String SUBSCRIBE = "subscribe";
  private static final String UNSUBSCRIBE = "unsubscribe";

  public static final String TRADES = "trades";
  public static final String ORDERBOOK = "books";
  public static final String ORDERBOOK5 = "books5";
  public static final String FUNDING_RATE = "funding-rate";
  public static final String TICKERS = "tickers";
  public static final String USERTRADES = "orders";
  public static final String POSITIONS = "positions";
  public static final String BALANCE_AND_POSITION = "balance_and_position";

  private final Observable<Long> pingPongSrc = Observable.interval(15, 15, TimeUnit.SECONDS);

  private WebSocketClientHandler.WebSocketMessageHandler channelInactiveHandler = null;

  private Disposable pingPongSubscription;

  private final ExchangeSpecification xSpec;

  private volatile boolean loginDone = false;

  private volatile boolean needToResubscribeChannels = false;

  public OkexStreamingService(String apiUrl, ExchangeSpecification exchangeSpecification) {
    super(apiUrl);
    this.xSpec = exchangeSpecification;
  }

  @Override
  public Completable connect() {
    loginDone = xSpec.getApiKey() == null;
    Completable conn = super.connect();
    return conn.andThen(
        (CompletableSource)
            (completable) -> {
              try {
                if (xSpec.getApiKey() != null) {
                  login();
                }

                if (pingPongSubscription != null && !pingPongSubscription.isDisposed()) {
                  pingPongSubscription.dispose();
                }

                pingPongSubscription = pingPongSrc.subscribe(o -> this.sendMessage("ping"));
                completable.onComplete();
              } catch (Exception e) {
                completable.onError(e);
              }
            });
  }

  @Override
  public void resubscribeChannels() {
    needToResubscribeChannels = true;
    if (loginDone) {
      super.resubscribeChannels();
    }
  }

  public void login() throws JsonProcessingException {
    Mac mac;
    try {
      mac = Mac.getInstance(BaseParamsDigest.HMAC_SHA_256);
      final SecretKey secretKey =
          new SecretKeySpec(
              xSpec.getSecretKey().getBytes(StandardCharsets.UTF_8), BaseParamsDigest.HMAC_SHA_256);
      mac.init(secretKey);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      throw new ExchangeException("Invalid API secret", e);
    }
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String toSign = timestamp + LOGIN_SIGN_METHOD + LOGIN_SIGN_REQUEST_PATH;
    String sign =
        Base64.getEncoder().encodeToString(mac.doFinal(toSign.getBytes(StandardCharsets.UTF_8)));

    OkexLoginMessage message = new OkexLoginMessage();
    String passphrase = xSpec.getExchangeSpecificParametersItem("passphrase").toString();
    OkexLoginMessage.LoginArg loginArg =
        new OkexLoginMessage.LoginArg(xSpec.getApiKey(), passphrase, timestamp, sign);
    message.getArgs().add(loginArg);

    this.sendMessage(objectMapper.writeValueAsString(message));
  }

  @Override
  public void messageHandler(String message) {
    LOG.debug("Received message: {}", message);
    JsonNode jsonNode;

    // Parse incoming message to JSON
    try {
      jsonNode = objectMapper.readTree(message);
    } catch (IOException e) {
      if ("pong".equals(message)) {
        // ping pong message
        return;
      }
      LOG.error("Error parsing incoming message to JSON: {}", message);
      return;
    }

    if (jsonNode.has("event")) {
      String event = jsonNode.get("event").asText();
      if ("login".equals(event)) {
        loginDone = true;
        if (needToResubscribeChannels) {
          this.resubscribeChannels();
          needToResubscribeChannels = false;
        }
        return;
      }
    }

    if (processArrayMessageSeparately() && jsonNode.isArray()) {
      // In case of array - handle every message separately.
      for (JsonNode node : jsonNode) {
        handleMessage(node);
      }
    } else {
      handleMessage(jsonNode);
    }
  }

  @Override
  protected String getChannelNameFromMessage(JsonNode message) {
    String channelName = "";
    if (message.has("arg")) {
      if (message.get("arg").has("channel") && message.get("arg").has("instId")) {
        channelName =
            message.get("arg").get("channel").asText() + message.get("arg").get("instId").asText();
      }
      if (!this.channels.containsKey(channelName)) {
        try {
          SubscriptionTopic subscriptionTopic = objectMapper.treeToValue(message.get("arg"),
              SubscriptionTopic.class);
          channelName = generateSubscriptionUniqueId(subscriptionTopic);
        } catch (JsonProcessingException ignored) {
        }
      }
    }
    return channelName;
  }

  @Override
  public String getSubscriptionUniqueId(String channelName, Object... args) {
    if (args.length > 0 && args[0] instanceof SubscriptionTopic) {
      SubscriptionTopic topic = (SubscriptionTopic) args[0];
      return generateSubscriptionUniqueId(topic);
    }
    return super.getSubscriptionUniqueId(channelName, args);
  }

  private String generateSubscriptionUniqueId(SubscriptionTopic topic) {
    return generateSubscriptionUniqueId(topic.getChannel(), topic.getInstId(), topic.getInstType(),
        topic.getInstFamily());
  }

  private String generateSubscriptionUniqueId(String channel, String instId, OkexInstType instType,
      String instFamily) {

    return String.format("[%s]-[%s]-[%s]-[%s]",
        channel,
        instType == null ? "" : instType.name(),
        StringUtils.isBlank(instId) ? "" : instId,
        StringUtils.isBlank(instFamily) ? "" : instFamily
    );
  }

  @Override
  public String getSubscribeMessage(String channelName, Object... args) throws IOException {
    if (args.length > 0 && args[0] instanceof SubscriptionTopic) {
      SubscriptionTopic topic = (SubscriptionTopic) args[0];
      return objectMapper.writeValueAsString(
          new OkexSubscribeMessage(SUBSCRIBE, Collections.singletonList(topic)));
    }
    return objectMapper.writeValueAsString(
        new OkexSubscribeMessage(SUBSCRIBE, Collections.singletonList(getTopic(channelName))));
  }

  @Override
  public String getUnsubscribeMessage(String channelName, Object... args) throws IOException {
    if (args.length > 0 && args[0] instanceof SubscriptionTopic) {
      SubscriptionTopic topic = (SubscriptionTopic) args[0];
      return objectMapper.writeValueAsString(
          new OkexSubscribeMessage(UNSUBSCRIBE, Collections.singletonList(topic)));
    }

    return objectMapper.writeValueAsString(
        new OkexSubscribeMessage(UNSUBSCRIBE, Collections.singletonList(getTopic(channelName))));
  }

  private OkexSubscribeMessage.SubscriptionTopic getTopic(String channelName) {
    if (channelName.contains(ORDERBOOK5)) {
      return new OkexSubscribeMessage.SubscriptionTopic(
          ORDERBOOK5, null, null, channelName.replace(ORDERBOOK5, ""));
    } else if (channelName.contains(ORDERBOOK)) {
      return new OkexSubscribeMessage.SubscriptionTopic(
          ORDERBOOK, null, null, channelName.replace(ORDERBOOK, ""));
    } else if (channelName.contains(TRADES)) {
      return new OkexSubscribeMessage.SubscriptionTopic(
          TRADES, null, null, channelName.replace(TRADES, ""));
    } else if (channelName.contains(TICKERS)) {
      return new OkexSubscribeMessage.SubscriptionTopic(
          TICKERS, null, null, channelName.replace(TICKERS, ""));
    } else if (channelName.contains(USERTRADES)) {
      return new OkexSubscribeMessage.SubscriptionTopic(
          USERTRADES, OkexInstType.ANY, null, channelName.replace(USERTRADES, ""));
    } else if (channelName.contains(FUNDING_RATE)) {
      return new OkexSubscribeMessage.SubscriptionTopic(
          FUNDING_RATE, null, null, channelName.replace(FUNDING_RATE, ""));
    } else if (channelName.contains(POSITIONS)) {
      return new OkexSubscribeMessage.SubscriptionTopic(
          POSITIONS, OkexInstType.ANY, null, channelName.replace(POSITIONS, ""));
    } else {
      throw new NotYetImplementedForExchangeException(
          "ChannelName: "
              + channelName
              + " has not implemented yet on "
              + this.getClass().getSimpleName());
    }
  }

  @Override
  protected WebSocketClientHandler getWebSocketClientHandler(
      WebSocketClientHandshaker handshake, WebSocketClientHandler.WebSocketMessageHandler handler) {
    LOG.info("Registering OkxWebSocketClientHandler");
    return new OkxWebSocketClientHandler(handshake, handler);
  }

  public void setChannelInactiveHandler(
      WebSocketClientHandler.WebSocketMessageHandler channelInactiveHandler) {
    this.channelInactiveHandler = channelInactiveHandler;
  }

  /**
   * Custom client handler in order to execute an external, user-provided handler on channel
   * events.
   */
  class OkxWebSocketClientHandler extends NettyWebSocketClientHandler {

    public OkxWebSocketClientHandler(
        WebSocketClientHandshaker handshake, WebSocketMessageHandler handler) {
      super(handshake, handler);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
      super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
      super.channelInactive(ctx);
      if (channelInactiveHandler != null) {
        channelInactiveHandler.onMessage("WebSocket Client disconnected!");
      }
    }
  }

  public void pingPongDisconnectIfConnected() {
    if (pingPongSubscription != null && !pingPongSubscription.isDisposed()) {
      pingPongSubscription.dispose();
    }
  }
}
