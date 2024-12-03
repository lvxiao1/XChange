package org.knowm.xchange.bitget.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractPlaceOrderDto {
  /**
   * The trading pair name, e.g., "ETHUSDT".
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * The product type, indicating the type of futures contract:
   * <ul>
   *   <li>USDT-FUTURES - USDT perpetual futures</li>
   *   <li>COIN-FUTURES - Coin-margined futures</li>
   *   <li>USDC-FUTURES - USDC perpetual futures</li>
   *   <li>SUSDT-FUTURES - Simulated USDT perpetual futures</li>
   *   <li>SCOIN-FUTURES - Simulated coin-margined futures</li>
   *   <li>SUSDC-FUTURES - Simulated USDC perpetual futures</li>
   * </ul>
   */
  @JsonProperty("productType")
  private String productType;

  /**
   * The margin mode for the position:
   * <ul>
   *   <li>isolated - Isolated margin</li>
   *   <li>crossed - Cross margin</li>
   * </ul>
   */
  @JsonProperty("marginMode")
  private String marginMode;

  /**
   * The margin currency (in uppercase), e.g., "USDT".
   */
  @JsonProperty("marginCoin")
  private String marginCoin;

  /**
   * The order quantity (in base currency).
   */
  @JsonProperty("size")
  private BigDecimal size;

  /**
   * The order price (required when "orderType" is limit).
   */
  @JsonProperty("price")
  private BigDecimal price;

  /**
   * The trading direction:
   * <ul>
   *   <li>buy - Buy (long position in two-way mode)</li>
   *   <li>sell - Sell (short position in two-way mode)</li>
   * </ul>
   */
  @JsonProperty("side")
  private String side;

  /**
   * The trading type (required in two-way position mode):
   * <ul>
   *   <li>open - Open position</li>
   *   <li>close - Close position</li>
   * </ul>
   */
  @JsonProperty("tradeSide")
  private String tradeSide;

  /**
   * The order type:
   * <ul>
   *   <li>limit - Limit order</li>
   *   <li>market - Market order</li>
   * </ul>
   */
  @JsonProperty("orderType")
  private String orderType;

  /**
   * The order validity period (only for limit orders):
   * <ul>
   *   <li>ioc - Immediate or cancel</li>
   *   <li>fok - Fill or kill</li>
   *   <li>gtc - Good till canceled (default)</li>
   *   <li>post_only - Maker-only</li>
   * </ul>
   */
  @JsonProperty("force")
  private String force;

  /**
   * Custom order ID.
   */
  @JsonProperty("clientOid")
  private String clientOid;

  /**
   * Reduce-only flag (only applicable in one-way position mode):
   * <ul>
   *   <li>YES - Reduce only</li>
   *   <li>NO - Default</li>
   * </ul>
   */
  @JsonProperty("reduceOnly")
  private String reduceOnly;

  /**
   * Pre-set take profit price (optional).
   */
  @JsonProperty("presetStopSurplusPrice")
  private BigDecimal presetStopSurplusPrice;

  /**
   * Pre-set stop loss price (optional).
   */
  @JsonProperty("presetStopLossPrice")
  private BigDecimal presetStopLossPrice;

  /**
   * Self-trade prevention (STP) mode:
   * <ul>
   *   <li>none - No STP (default)</li>
   *   <li>cancel_taker - Cancel taker order</li>
   *   <li>cancel_maker - Cancel maker order</li>
   *   <li>cancel_both - Cancel both orders</li>
   * </ul>
   */
  @JsonProperty("stpMode")
  private String stpMode;
}
