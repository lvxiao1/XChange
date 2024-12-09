package org.knowm.xchange.bitget.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BitgetContractLeverageDto {

  /**
   * Symbol name
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * Margin coin
   */
  @JsonProperty("marginCoin")
  private String marginCoin;

  /**
   * Long position leverage
   */
  @JsonProperty("longLeverage")
  private BigDecimal longLeverage;

  /**
   * Short position leverage
   */
  @JsonProperty("shortLeverage")
  private BigDecimal shortLeverage;

  /**
   * Cross position leverage
   */
  @JsonProperty("crossMarginLeverage")
  private BigDecimal crossMarginLeverage;

  /**
   * Margin mode. isolated: isolated mode; crossed: cross mode
   */
  @JsonProperty("marginMode")
  private String marginMode;
}
