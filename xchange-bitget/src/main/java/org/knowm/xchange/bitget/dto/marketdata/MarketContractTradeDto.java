package org.knowm.xchange.bitget.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class MarketContractTradeDto {

  /**
   * Trade ID, sorted in descending order.
   */
  @JsonProperty("tradeId")
  private String tradeId;

  /**
   * Price of the trade.
   */
  @JsonProperty("price")
  private BigDecimal price;

  /**
   * Size of the trade. Represents the quantity of the asset traded.
   */
  @JsonProperty("size")
  private BigDecimal size;

  /**
   * The direction of the trade: "sell" for buy orders, "buy" for sell orders.
   */
  @JsonProperty("side")
  private String side;

  /**
   * Timestamp of the data in Unix format (milliseconds).
   */
  @JsonProperty("ts")
  private Long ts;

  /**
   * The trading pair symbol (e.g., ETHUSDT).
   */
  @JsonProperty("symbol")
  private String symbol;
}
