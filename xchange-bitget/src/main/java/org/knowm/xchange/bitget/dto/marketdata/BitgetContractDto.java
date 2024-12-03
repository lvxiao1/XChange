package org.knowm.xchange.bitget.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * Represents the trading pair configuration and related parameters.
 */
@Data
public class BitgetContractDto {

  /**
   * The product name.
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * The base currency (e.g., ETH in ETHUSDT).
   */
  @JsonProperty("baseCoin")
  private String baseCoin;

  /**
   * The quote currency (e.g., USDT in ETHUSDT).
   */
  @JsonProperty("quoteCoin")
  private String quoteCoin;

  /**
   * Buy price limit ratio.
   */
  @JsonProperty("buyLimitPriceRatio")
  private BigDecimal buyLimitPriceRatio;

  /**
   * Sell price limit ratio.
   */
  @JsonProperty("sellLimitPriceRatio")
  private BigDecimal sellLimitPriceRatio;

  /**
   * Fee rate increase ratio.
   */
  @JsonProperty("feeRateUpRatio")
  private BigDecimal feeRateUpRatio;

  /**
   * Maker fee rate.
   */
  @JsonProperty("makerFeeRate")
  private BigDecimal makerFeeRate;

  /**
   * Taker fee rate.
   */
  @JsonProperty("takerFeeRate")
  private BigDecimal takerFeeRate;

  /**
   * Open cost increase ratio.
   */
  @JsonProperty("openCostUpRatio")
  private BigDecimal openCostUpRatio;

  /**
   * List of supported margin currencies.
   */
  @JsonProperty("supportMarginCoins")
  private List<String> supportMarginCoins;

  /**
   * Minimum trade quantity (in base currency).
   */
  @JsonProperty("minTradeNum")
  private BigDecimal minTradeNum;

  /**
   * Price step size.
   */
  @JsonProperty("priceEndStep")
  private String priceEndStep;

  /**
   * Number of decimal places for quantity.
   */
  @JsonProperty("volumePlace")
  private BigDecimal volumePlace;

  /**
   * Number of decimal places for price.
   */
  @JsonProperty("pricePlace")
  private BigDecimal pricePlace;

  /**
   * Quantity multiplier. The order quantity must be greater than minTradeNum and a multiple of this
   * value.
   */
  @JsonProperty("sizeMultiplier")
  private BigDecimal sizeMultiplier;

  /**
   * Contract type: perpetual or delivery.
   */
  @JsonProperty("symbolType")
  private String symbolType;

  /**
   * Minimum USDT trade amount.
   */
  @JsonProperty("minTradeUSDT")
  private BigDecimal minTradeUSDT;

  /**
   * Maximum number of orders per symbol.
   */
  @JsonProperty("maxSymbolOrderNum")
  private BigDecimal maxSymbolOrderNum;

  /**
   * Maximum number of orders per product type.
   */
  @JsonProperty("maxProductOrderNum")
  private BigDecimal maxProductOrderNum;

  /**
   * Maximum number of positions.
   */
  @JsonProperty("maxPositionNum")
  private BigDecimal maxPositionNum;

  /**
   * Status of the trading pair:
   * <ul>
   *   <li>listed - listed</li>
   *   <li>normal - normal/open</li>
   *   <li>maintain - trading prohibited</li>
   *   <li>limit_open - restricted opening</li>
   *   <li>restrictedAPI - API restricted for orders</li>
   *   <li>off - delisted</li>
   * </ul>
   */
  @JsonProperty("symbolStatus")
  private String symbolStatus;

  /**
   * Delisting time. '-1' indicates normal status.
   */
  @JsonProperty("offTime")
  private String offTime;

  /**
   * Time when opening positions is allowed. '-1' indicates normal status. Other values indicate
   * planned maintenance.
   */
  @JsonProperty("limitOpenTime")
  private Long limitOpenTime;

  /**
   * Delivery time.
   */
  @JsonProperty("deliveryTime")
  private Long deliveryTime;

  /**
   * Delivery start time.
   */
  @JsonProperty("deliveryStartTime")
  private Long deliveryStartTime;

  /**
   * Delivery period:
   * <ul>
   *   <li>this_quarter - current quarter</li>
   *   <li>next_quarter - next quarter</li>
   * </ul>
   */
  @JsonProperty("deliveryPeriod")
  private String deliveryPeriod;

  /**
   * Listing time.
   */
  @JsonProperty("launchTime")
  private Long launchTime;

  /**
   * Funding fee settlement interval (e.g., hourly or every 8 hours).
   */
  @JsonProperty("fundInterval")
  private Integer fundInterval;

  /**
   * Minimum leverage.
   */
  @JsonProperty("minLever")
  private Integer minLever;

  /**
   * Maximum leverage.
   */
  @JsonProperty("maxLever")
  private Integer maxLever;

  /**
   * Position limit.
   */
  @JsonProperty("posLimit")
  private BigDecimal posLimit;

  /**
   * Maintenance time (available when status is or will be under maintenance).
   */
  @JsonProperty("maintainTime")
  private Long maintainTime;
}
