package org.knowm.xchange.bitget.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BitgetContractTickerDto {

  /**
   * The trading pair name.
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * The latest transaction price.
   */
  @JsonProperty("lastPr")
  private BigDecimal lastPr;

  /**
   * Best ask price.
   */
  @JsonProperty("askPr")
  private BigDecimal askPr;

  /**
   * Best bid price.
   */
  @JsonProperty("bidPr")
  private BigDecimal bidPr;

  /**
   * Bid size (quantity at the best bid price).
   */
  @JsonProperty("bidSz")
  private BigDecimal bidSz;

  /**
   * Ask size (quantity at the best ask price).
   */
  @JsonProperty("askSz")
  private BigDecimal askSz;

  /**
   * Highest price in the last 24 hours.
   */
  @JsonProperty("high24h")
  private BigDecimal high24h;

  /**
   * Lowest price in the last 24 hours.
   */
  @JsonProperty("low24h")
  private BigDecimal low24h;

  /**
   * Timestamp of the current data, in milliseconds (Unix timestamp format).
   */
  @JsonProperty("ts")
  private Long ts;

  /**
   * 24-hour price change percentage.
   */
  @JsonProperty("change24h")
  private BigDecimal change24h;

  /**
   * Trading volume in the base currency.
   */
  @JsonProperty("baseVolume")
  private BigDecimal baseVolume;

  /**
   * Trading volume in the quote currency.
   */
  @JsonProperty("quoteVolume")
  private BigDecimal quoteVolume;

  /**
   * Trading volume in USDT.
   */
  @JsonProperty("usdtVolume")
  private BigDecimal usdtVolume;

  /**
   * Opening price at UTC+0 time zone.
   */
  @JsonProperty("openUtc")
  private String openUtc;

  /**
   * 24-hour price change percentage in the UTC+0 time zone.
   */
  @JsonProperty("changeUtc24h")
  private String changeUtc24h;

  /**
   * Index price.
   */
  @JsonProperty("indexPrice")
  private BigDecimal indexPrice;

  /**
   * Funding rate.
   */
  @JsonProperty("fundingRate")
  private BigDecimal fundingRate;

  /**
   * Current position amount, in base currency.
   */
  @JsonProperty("holdingAmount")
  private BigDecimal holdingAmount;

  /**
   * Opening price for the last 24 hours. Calculated based on a 24-hour window from the current
   * time.
   */
  @JsonProperty("open24h")
  private BigDecimal open24h;

  /**
   * Delivery start time (only applicable to delivery contracts).
   */
  @JsonProperty("deliveryStartTime")
  private Long deliveryStartTime;

  /**
   * Delivery time (only applicable to delivery contracts).
   */
  @JsonProperty("deliveryTime")
  private Long deliveryTime;

  /**
   * Delivery status (only applicable to delivery contracts):
   * <ul>
   *   <li>delivery_config_period - New trading pair configuration</li>
   *   <li>delivery_normal - Normal trading</li>
   *   <li>delivery_before - 10 minutes before delivery, opening positions are prohibited</li>
   *   <li>delivery_period - During delivery, opening/closing and order cancellations are prohibited</li>
   * </ul>
   */
  @JsonProperty("deliveryStatus")
  private String deliveryStatus;

  /**
   * Mark price.
   */
  @JsonProperty("markPrice")
  private BigDecimal markPrice;
}
