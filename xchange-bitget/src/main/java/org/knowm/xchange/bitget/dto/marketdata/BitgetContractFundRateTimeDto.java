package org.knowm.xchange.bitget.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BitgetContractFundRateTimeDto {

  /**
   * The trading pair name.
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * The next funding settlement time in milliseconds.
   */
  @JsonProperty("nextFundingTime")
  private Long nextFundingTime;

  /**
   * The funding rate settlement period, in hours.
   */
  @JsonProperty("ratePeriod")
  private Integer ratePeriod;

}
