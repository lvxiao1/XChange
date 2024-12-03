package org.knowm.xchange.bitget.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BitgetContractFundRateDto {

  /**
   * The trading pair name.
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * The current funding rate.
   */
  @JsonProperty("fundingRate")
  private BigDecimal fundingRate;
}
