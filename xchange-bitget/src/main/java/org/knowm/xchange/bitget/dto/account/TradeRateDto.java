package org.knowm.xchange.bitget.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeRateDto {
  @JsonProperty("makerFeeRate")
  private BigDecimal makerFeeRate;
  @JsonProperty("takerFeeRate")
  private BigDecimal takerFeeRate;
}
