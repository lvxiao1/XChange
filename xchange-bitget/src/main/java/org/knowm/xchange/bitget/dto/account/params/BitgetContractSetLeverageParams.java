package org.knowm.xchange.bitget.dto.account.params;

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
public class BitgetContractSetLeverageParams {

  /**
   * Trading symbol pair
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * Product type
   * USDT-FUTURES USDT professional contract
   * COIN-FUTURES Mixed contract
   * USDC-FUTURES USDC professional contract
   * SUSDT-FUTURES USDT professional contract simulation
   * SCOIN-FUTURES Mixed contract simulation
   * SUSDC-FUTURES USDC professional contract simulation
   */
  @JsonProperty("productType")
  private String productType;

  /**
   * Margin coin, must be uppercase
   */
  @JsonProperty("marginCoin")
  private String marginCoin;

  /**
   * Leverage multiple
   */
  @JsonProperty("leverage")
  private BigDecimal leverage;

  /**
   * Position side (not passed in full position mode, this parameter will be ignored)
   * long: long position;
   * short: short position
   */
  @JsonProperty("holdSide")
  private String holdSide;
}
