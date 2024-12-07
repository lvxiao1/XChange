package org.knowm.xchange.bitget.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBillDto {

  @JsonProperty("endId")
  private String endId;

  @JsonProperty("bills")
  private List<Bill> bills;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Bill {

    /**
     * The bill ID.
     */
    @JsonProperty("billId")
    private String billId;

    /**
     * The symbol or trading pair name.
     */
    @JsonProperty("symbol")
    private String symbol;

    /**
     * The transaction amount.
     */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
     * The transaction fee.
     */
    @JsonProperty("fee")
    private BigDecimal fee;

    /**
     * Fee deduction via coupons.
     */
    @JsonProperty("feeByCoupon")
    private String feeByCoupon;

    /**
     * The business type.
     * <ul>
     * <li>unknown: Unknown operation</li>
     * <li>trans_from_exchange: Transferred from spot account</li>
     * <li>trans_to_exchange: Transferred to spot account</li>
     * <li>open_long: Open long position</li>
     * <li>open_short: Open short position</li>
     * <li>close_long: Close long position</li>
     * <li>close_short: Close short position</li>
     * <li>force_close_long: Force reduce long position</li>
     * <li>force_close_short: Force reduce short position</li>
     * <li>contract_settle_fee: Funding fee</li>
     * <li>append_margin: Adjust position margin</li>
     * <li>adjust_down_lever_append_margin: Reduce leverage and increase margin</li>
     * <li>reduce_margin: Reduce position margin</li>
     * <li>auto_append_margin: Auto-added margin</li>
     * <li>cash_gift_issue: Experience/activity gift issued</li>
     * <li>cash_gift_recycle: Experience/activity gift recycled</li>
     * <li>tracking_follow_pay: Settlement deduction for copy trading</li>
     * <li>tracking_follow_back: Refund settlement for copy trading</li>
     * <li>tracking_trader_income: Profit sharing for copy trading</li>
     * <li>burst_long_loss_query: Long liquidation</li>
     * <li>burst_short_loss_query: Short liquidation</li>
     * <li>trans_from_contract: Transferred from contract account</li>
     * <li>trans_to_contract: Transferred to contract account</li>
     * <li>trans_from_otc: Transferred from OTC account</li>
     * <li>trans_to_otc: Transferred to OTC account</li>
     * <li>buy: One-way position buy</li>
     * <li>sell: One-way position sell</li>
     * <li>force_buy: One-way position forced buy</li>
     * <li>force_sell: One-way position forced sell</li>
     * <li>burst_buy: Liquidation buy</li>
     * <li>burst_sell: Liquidation sell</li>
     * <li>bonus_issue: Bonus issued</li>
     * <li>bonus_recycle: Bonus recycled</li>
     * <li>bonus_expired: Bonus expired</li>
     * <li>delivery_long: Long delivery</li>
     * <li>delivery_short: Short delivery</li>
     * <li>trans_from_cross: Transferred from cross-margin account</li>
     * <li>trans_to_cross: Transferred to cross-margin account</li>
     * <li>trans_from_isolated: Transferred from isolated-margin account</li>
     * <li>trans_to_isolated: Transferred to isolated-margin account</li>
     * </ul>
     */
    @JsonProperty("businessType")
    private String businessType;

    /**
     * The currency, e.g., USDT.
     */
    @JsonProperty("coin")
    private String coin;

    /**
     * The balance after the transaction.
     */
    @JsonProperty("balance")
    private BigDecimal balance;

    /**
     * The creation timestamp in milliseconds.
     */
    @JsonProperty("cTime")
    private Long cTime;
  }
}
