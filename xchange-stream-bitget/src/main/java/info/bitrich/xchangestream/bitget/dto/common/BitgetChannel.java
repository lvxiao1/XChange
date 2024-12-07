package info.bitrich.xchangestream.bitget.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BitgetChannel {

  @JsonProperty("instType")
  private MarketType marketType;

  @JsonProperty("channel")
  private ChannelType channelType;

  @JsonProperty("instId")
  private String instrumentId;

  @Getter
  @AllArgsConstructor
  public static enum MarketType {
    SPOT("SPOT"),
    USDT_FUTURES("USDT-FUTURES"),
    COIN_FUTURES("COIN-FUTURES"),
    USDC_FUTURES("USDC-FUTURES"),
    SUSDT_FUTURES("SUSDT-FUTURES"),
    SCOIN_FUTURES("SCOIN-FUTURES"),
    SUSDC_FUTURES("SUSDC-FUTURES");
    @JsonValue private final String value;
    public boolean isFutures() {
      return this.value.contains("-FUTURES");
    }
    public String toString() {
      return value;
    }
  }

  @Getter
  @AllArgsConstructor
  public static enum ChannelType {
    TICKER("ticker"),
    ORDERS("orders"),
    DEPTH("books"),
    DEPTH1("books1"),
    DEPTH5("books5"),
    DEPTH15("books15"),
    POSITIONS("positions"),
    TRADE("trade"),
    FILL("fill");

    @JsonValue private final String value;

    public String toString() {
      return value;
    }
  }
}
