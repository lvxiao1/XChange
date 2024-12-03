package org.knowm.xchange.bitget.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContractCancelOrderDto {
  @JsonProperty("orderId")
  private String orderId;
  @JsonProperty("clientOid")
  private String clientOid;
  @JsonProperty("productType")
  private String productType;
  @JsonProperty("symbol")
  private String symbol;
}
