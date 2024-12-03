package org.knowm.xchange.bitget.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ContractOrderDetailDto {

  /**
   * The trading pair name, e.g., "ETHUSDT".
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * The order quantity.
   */
  @JsonProperty("size")
  private BigDecimal size;

  /**
   * The order ID.
   */
  @JsonProperty("orderId")
  private String orderId;

  /**
   * Custom order ID.
   */
  @JsonProperty("clientOid")
  private String clientOid;

  /**
   * The base currency volume traded.
   */
  @JsonProperty("baseVolume")
  private BigDecimal baseVolume;

  /**
   * The average execution price.
   */
  @JsonProperty("priceAvg")
  private BigDecimal priceAvg;

  /**
   * The fee charged for the trade.
   */
  @JsonProperty("fee")
  private BigDecimal fee;

  /**
   * The order price.
   */
  @JsonProperty("price")
  private BigDecimal price;

  /**
   * The order state.
   * <ul>
   *   <li>live - New order, waiting in the order book</li>
   *   <li>partially_filled - Partially filled</li>
   *   <li>filled - Fully filled</li>
   *   <li>canceled - Canceled</li>
   * </ul>
   */
  @JsonProperty("state")
  private String state;

  /**
   * The order direction:
   * <ul>
   *   <li>buy - Buy</li>
   *   <li>sell - Sell</li>
   * </ul>
   */
  @JsonProperty("side")
  private String side;

  /**
   * The order validity period:
   * <ul>
   *   <li>ioc - Immediate or cancel</li>
   *   <li>fok - Fill or kill</li>
   *   <li>gtc - Good till canceled</li>
   *   <li>post_only - Maker only</li>
   * </ul>
   */
  @JsonProperty("force")
  private String force;

  /**
   * The total profit or loss from the trade.
   */
  @JsonProperty("totalProfits")
  private BigDecimal totalProfits;

  /**
   * The position direction:
   * <ul>
   *   <li>long - Long position</li>
   *   <li>short - Short position</li>
   *   <li>net - One-way position</li>
   * </ul>
   */
  @JsonProperty("posSide")
  private String posSide;

  /**
   * The margin currency, e.g., "USDT".
   */
  @JsonProperty("marginCoin")
  private String marginCoin;

  /**
   * Pre-set take profit price.
   */
  @JsonProperty("presetStopSurplusPrice")
  private BigDecimal presetStopSurplusPrice;

  /**
   * Pre-set stop loss price.
   */
  @JsonProperty("presetStopLossPrice")
  private BigDecimal presetStopLossPrice;

  /**
   * The quote currency volume traded.
   */
  @JsonProperty("quoteVolume")
  private BigDecimal quoteVolume;

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
   * The leverage applied, e.g., "10x".
   */
  @JsonProperty("leverage")
  private Integer leverage;

  /**
   * The margin mode:
   * <ul>
   *   <li>isolated - Isolated margin</li>
   *   <li>crossed - Cross margin</li>
   * </ul>
   */
  @JsonProperty("marginMode")
  private String marginMode;

  /**
   * Whether the order is reduce-only:
   * <ul>
   *   <li>YES - Reduce only</li>
   *   <li>NO - Default</li>
   * </ul>
   */
  @JsonProperty("reduceOnly")
  private String reduceOnly;

  /**
   * The source of the order:
   * <ul>
   *   <li>WEB - Created from web</li>
   *   <li>API - Created from API</li>
   *   <li>SYS - System-managed order</li>
   *   <li>ANDROID - Created from Android app</li>
   *   <li>IOS - Created from iOS app</li>
   * </ul>
   */
  @JsonProperty("enterPointSource")
  private String enterPointSource;

  /**
   * The trade direction in position mode.
   */
  @JsonProperty("tradeSide")
  private String tradeSide;

  /**
   * The position mode:
   * <ul>
   *   <li>one_way_mode - One-way position</li>
   *   <li>hedge_mode - Hedge (two-way) position</li>
   * </ul>
   */
  @JsonProperty("posMode")
  private String posMode;

  /**
   * The source of the order:
   */
  @JsonProperty("orderSource")
  private String orderSource;

  /**
   * The reason for cancellation.
   */
  @JsonProperty("cancelReason")
  private String cancelReason;

  /**
   * The order creation time in milliseconds.
   */
  @JsonProperty("cTime")
  private Long cTime;

  /**
   * The order update time in milliseconds.
   */
  @JsonProperty("uTime")
  private Long uTime;
}
