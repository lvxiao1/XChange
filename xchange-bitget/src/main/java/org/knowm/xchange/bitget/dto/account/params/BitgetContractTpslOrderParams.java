package org.knowm.xchange.bitget.dto.account.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitgetContractTpslOrderParams {
  /**
   * Margin coin in uppercase.
   * Example: BTC, ETH
   */
  @JsonProperty("marginCoin")
  private String marginCoin;

  /**
   * Product type.
   * Possible values:
   * - usdt-futures: USDT professional futures
   * - coin-futures: Mixed futures
   * - usdc-futures: USDC professional futures
   * - susdt-futures: USDT professional futures simulation
   * - scoin-futures: Mixed futures simulation
   * - susdc-futures: USDC professional futures simulation
   */
  @JsonProperty("productType")
  private String productType;

  /**
   * Trading symbol.
   * Example: BTC-USDT, ETH-USDT
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * Profit and stop-loss plan type.
   * Possible values:
   * - profit_plan: Profit plan
   * - loss_plan: Loss plan
   * - moving_plan: Moving profit and stop-loss plan
   * - pos_profit: Position profit
   * - pos_loss: Position loss
   */
  @JsonProperty("planType")
  private String planType;

  /**
   * Trigger price.
   * Example: 10000.00
   */
  @JsonProperty("triggerPrice")
  private BigDecimal triggerPrice;

  /**
   * Trigger type.
   * Possible values:
   * - fill_price: Market price
   * - mark_price: Mark price
   */
  @JsonProperty("triggerType")
  private String triggerType;

  /**
   * Execution price.
   * If not provided or set to 0, it will be executed at market price.
   * If greater than 0, it will be executed at a limit price.
   * Note: This parameter is not allowed when planType is moving_plan and will be fixed as market price.
   */
  @JsonProperty("executePrice")
  private BigDecimal executePrice;

  /**
   * Holding side.
   * Possible values for two-way holding:
   * - long: Long position
   * - short: Short position
   * Possible values for one-way holding:
   * - buy: Long position
   * - sell: Short position
   */
  @JsonProperty("holdSide")
  private String holdSide;

  /**
   * Order size (base currency).
   * Required when planType is profit_plan, loss_plan, or moving_plan (must be greater than 0).
   * Optional when planType is pos_profit or pos_loss.
   */
  @JsonProperty("size")
  private BigDecimal size;

  /**
   * Callback rate.
   * Required when planType is moving_plan.
   */
  @JsonProperty("rangeRate")
  private String rangeRate;

  /**
   * Custom order ID.
   */
  @JsonProperty("clientOid")
  private String clientOid;

  /**
   * STP mode.
   * Possible values:
   * - none: No STP setting
   * - cancel_taker: Cancel taker orders
   * - cancel_maker: Cancel maker orders
   * - cancel_both: Cancel both taker and maker orders
   */
  @JsonProperty("stpMode")
  private String stpMode;
}
