package info.bitrich.xchangestream.okex;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.bitrich.xchangestream.core.StreamingAccountService;
import info.bitrich.xchangestream.okex.dto.OkexBalanceAndPositionMessage;
import info.bitrich.xchangestream.okex.dto.OkexPositionChangesMessage;
import info.bitrich.xchangestream.okex.dto.OkexSubscribeMessage.SubscriptionTopic;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import org.knowm.xchange.okex.dto.OkexInstType;

public class OkexStreamingAccountService implements StreamingAccountService {

  private final OkexStreamingService service;
  private final ObjectMapper mapper = StreamingObjectMapperHelper.getObjectMapper();

  public OkexStreamingAccountService(OkexStreamingService service) {
    this.service = service;
  }

  public Observable<OkexPositionChangesMessage> getAllPositionsChanges() {
    return getPositionsChanges(null, null, null);
  }

  @NonNull
  public Observable<OkexPositionChangesMessage> getPositionsChanges(OkexInstType instType,
      String instFamily, String instId) {
    String channelUniqueId = OkexStreamingService.POSITIONS;
    SubscriptionTopic topic = new SubscriptionTopic(channelUniqueId,
        instType != null ? instType : OkexInstType.ANY, null, instFamily, instId);
    return service
        .subscribeChannel(channelUniqueId, topic)
        .filter(message -> message.has("data"))
        .flatMap(
            jsonNode -> {
              List<OkexPositionChangesMessage> messages =
                  mapper.treeToValue(
                      jsonNode.get("data"),
                      mapper
                          .getTypeFactory()
                          .constructCollectionType(List.class, OkexPositionChangesMessage.class));
              return Observable.fromIterable(messages);
            }
        );
  }

  public Observable<OkexBalanceAndPositionMessage> getBalanceAndPositions() {
    String channelUniqueId = OkexStreamingService.BALANCE_AND_POSITION;
    SubscriptionTopic topic = new SubscriptionTopic(channelUniqueId);
    return service
        .subscribeChannel(channelUniqueId, topic)
        .filter(message -> message.has("data"))
        .flatMap(
            jsonNode -> {
              List<OkexBalanceAndPositionMessage> message =
                  mapper.treeToValue(
                      jsonNode.get("data"),
                      mapper
                          .getTypeFactory()
                          .constructCollectionType(List.class, OkexBalanceAndPositionMessage.class));
              return Observable.fromIterable(message);
            }
        );
  }
}
