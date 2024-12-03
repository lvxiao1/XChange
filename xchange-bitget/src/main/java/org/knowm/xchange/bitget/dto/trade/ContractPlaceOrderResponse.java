package org.knowm.xchange.bitget.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContractPlaceOrderResponse {
  @JsonProperty("orderId")
  private String orderId;

  @JsonProperty("clientOid")
  private String clientOid;
}
