package info.bitrich.xchangestream.bitget;

import info.bitrich.xchangestream.bitget.dto.common.BitgetChannel.ChannelType;
import info.bitrich.xchangestream.bitget.dto.common.BitgetChannel.MarketType;
import info.bitrich.xchangestream.bitget.dto.response.BitGetWsContractPositionsNotification;
import info.bitrich.xchangestream.bitget.dto.response.BitgetWsContractOrderNotification;
import info.bitrich.xchangestream.bitget.dto.response.BitgetWsUserTradeNotification;
import info.bitrich.xchangestream.core.StreamingTradeService;
import io.reactivex.rxjava3.core.Observable;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.instrument.Instrument;

@AllArgsConstructor
public class BitgetStreamingTradeService implements StreamingTradeService {

  private final BitgetStreamingService service;

  @Override
  public Observable<UserTrade> getUserTrades(CurrencyPair currencyPair, Object... args) {
    return service
        .subscribeChannel(null, ChannelType.FILL, MarketType.SPOT, currencyPair)
        .map(BitgetWsUserTradeNotification.class::cast)
        .map(BitgetStreamingAdapters::toUserTrade);
  }

  @Override
  public Observable<UserTrade> getUserTrades() {
    return getUserTrades(null);
  }


  public Observable<BitgetWsContractOrderNotification.OrderInfo> getContractOrder(
      MarketType instType) {
    if (instType == null || !instType.isFutures()) {
      throw new IllegalArgumentException("Not implemented:" + instType);
    }
    return service
        .subscribeChannel(null, ChannelType.ORDERS, instType)
        .map(BitgetWsContractOrderNotification.class::cast)
        .flatMap(message -> Observable.fromIterable(message.getPayloadItems()));
  }

  public Observable<BitGetWsContractPositionsNotification.Positions> getContractPositions(
      String instType) {
    if (StringUtils.isBlank(instType)) {
      throw new IllegalArgumentException("instType is required");
    }
    MarketType marketType = MarketType.valueOf(instType.replaceAll("-", "_").toUpperCase());
    if (!marketType.isFutures()) {
      throw new IllegalArgumentException("Not implemented:" + instType);
    }
    return service
        .subscribeChannel(null, ChannelType.POSITIONS, marketType)
        .map(BitGetWsContractPositionsNotification.class::cast)
        .flatMap(message -> Observable.fromIterable(message.getPayloadItems()));
  }
}
