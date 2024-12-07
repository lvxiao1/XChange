package org.knowm.xchange.bitget.dto.account;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ContractAccountDto represents an account with various financial metrics and settings related to
 * margin and leveraging.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractAccountDto {

  /**
   * The type of margin coin.
   */
  @JsonProperty("marginCoin")
  private String marginCoin;

  /**
   * Amount locked (margin coin), locked when there are open orders.
   */
  @JsonProperty("locked")
  private BigDecimal locked;

  /**
   * Available quantity in the account.
   */
  @JsonProperty("available")
  private BigDecimal available;

  /**
   * Maximum cross margin available for opening positions (in margin coin).
   */
  @JsonProperty("crossedMaxAvailable")
  private BigDecimal crossedMaxAvailable;

  /**
   * Maximum isolated margin available for opening positions (in margin coin).
   */
  @JsonProperty("isolatedMaxAvailable")
  private BigDecimal isolatedMaxAvailable;

  /**
   * Maximum amount that can be transferred out.
   */
  @JsonProperty("maxTransferOut")
  private BigDecimal maxTransferOut;

  /**
   * Account equity (in margin coin), including unrealized profits and losses calculated using the
   * mark price.
   */
  @JsonProperty("accountEquity")
  private BigDecimal accountEquity;

  /**
   * Equity in the account converted to USDT.
   */
  @JsonProperty("usdtEquity")
  private BigDecimal usdtEquity;

  /**
   * Equity in the account converted to BTC.
   */
  @JsonProperty("btcEquity")
  private BigDecimal btcEquity;

  /**
   * Risk rate when in cross margin mode.
   */
  @JsonProperty("crossedRiskRate")
  private BigDecimal crossedRiskRate;

  /**
   * Leverage multiplier when in cross margin mode.
   */
  @JsonProperty("crossedMarginLeverage")
  private BigDecimal crossedMarginLeverage;

  /**
   * Long leverage when in isolated margin mode.
   */
  @JsonProperty("isolatedLongLever")
  private BigDecimal isolatedLongLever;

  /**
   * Short leverage when in isolated margin mode.
   */
  @JsonProperty("isolatedShortLever")
  private BigDecimal isolatedShortLever;

  /**
   * Margin mode, either isolated or crossed.
   */
  @JsonProperty("marginMode")
  private String marginMode;

  /**
   * Position mode, either one-way or hedge.
   */
  @JsonProperty("posMode")
  private String posMode;

  /**
   * Unrealized profit and loss.
   */
  @JsonProperty("unrealizedPL")
  private BigDecimal unrealizedPL;

  /**
   * Experience coupon balance.
   */
  @JsonProperty("coupon")
  private BigDecimal coupon;

  /**
   * Unrealized profit and loss in cross margin mode.
   */
  @JsonProperty("crossedUnrealizedPL")
  private BigDecimal crossedUnrealizedPL;

  /**
   * Unrealized profit and loss in isolated margin mode.
   */
  @JsonProperty("isolatedUnrealizedPL")
  private BigDecimal isolatedUnrealizedPL;

  /**
   * Asset margin mode, either union or single.
   */
  @JsonProperty("assetMode")
  private String assetMode;

}
