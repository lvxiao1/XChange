package org.knowm.xchange.okex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class OkexTrade {
  /**
   * The product ID, e.g., BTC-USDT.
   */
  @JsonProperty("instId")
  private String instId;

  /**
   * The latest transaction ID among aggregated trades.
   */
  @JsonProperty("tradeId")
  private String tradeId;

  /**
   * The trade price.
   */
  @JsonProperty("px")
  private BigDecimal px;

  /**
   * The trade quantity.
   */
  @JsonProperty("sz")
  private BigDecimal sz;

  /**
   * The trade direction.
   * Possible values:
   * - "buy"
   * - "sell"
   */
  @JsonProperty("side")
  private String side;

  /**
   * The trade timestamp in milliseconds (Unix time format), e.g., 1597026383085.
   */
  @JsonProperty("ts")
  private Date ts;

  /**
   * The number of aggregated order matches.
   */
  @JsonProperty("count")
  private Integer count;
}
