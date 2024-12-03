package info.bitrich.xchangestream.bitget.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import info.bitrich.xchangestream.bitget.dto.common.Action;
import info.bitrich.xchangestream.bitget.dto.common.BitgetChannel;
import info.bitrich.xchangestream.bitget.dto.common.Operation;
import java.util.List;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@JsonTypeInfo(
    use = Id.NAME,
    include = As.EXISTING_PROPERTY,
    property = "messageType",
    visible = true,
    defaultImpl = BitgetWsNotification.class)
@JsonSubTypes({
  @Type(value = BitgetEventNotification.class, name = "event"),
  @Type(value = BitgetTickerNotification.class, name = "ticker:SPOT"),
  @Type(value = BitgetWsOrderBookSnapshotNotification.class, name = "books1:SPOT"),
  @Type(value = BitgetWsOrderBookSnapshotNotification.class, name = "books5:SPOT"),
  @Type(value = BitgetWsOrderBookSnapshotNotification.class, name = "books15:SPOT"),
  @Type(value = BitgetWsUserTradeNotification.class, name = "fill:SPOT"),
  @Type(value = BitgetWsContractOrderNotification.class, name = "orders:USDT-FUTURES"),
  @Type(value = BitgetWsContractOrderNotification.class, name = "orders:COIN-FUTURES"),
  @Type(value = BitgetWsContractOrderNotification.class, name = "orders:USDC-FUTURES"),
  @Type(value = BitgetWsContractOrderNotification.class, name = "orders:SUSDT-FUTURES"),
  @Type(value = BitgetWsContractOrderNotification.class, name = "orders:SCOIN-FUTURES"),
  @Type(value = BitgetWsContractOrderNotification.class, name = "orders:SUSDC-FUTURES"),
  @Type(value = BitGetWsContractPositionsNotification.class, name = "positions:USDT-FUTURES"),
  @Type(value = BitGetWsContractPositionsNotification.class, name = "positions:COIN-FUTURES"),
  @Type(value = BitGetWsContractPositionsNotification.class, name = "positions:USDC-FUTURES"),
  @Type(value = BitGetWsContractPositionsNotification.class, name = "positions:SUSDT-FUTURES"),
  @Type(value = BitGetWsContractPositionsNotification.class, name = "positions:SCOIN-FUTURES"),
  @Type(value = BitGetWsContractPositionsNotification.class, name = "positions:SUSDC-FUTURES"),
})
@Data
@SuperBuilder(toBuilder = true)
@Jacksonized
public class BitgetWsNotification<T> {

  @JsonProperty("action")
  private Action action;

  @JsonProperty("op")
  private Operation operation;

  @JsonProperty("arg")
  private BitgetChannel channel;

  @Singular
  @JsonProperty("data")
  private List<T> payloadItems;
}
