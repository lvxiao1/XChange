package org.knowm.xchange.okex.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OkexSetPositionModeRequest {

  @JsonProperty("posMode")
  private String posMode;

  public OkexSetPositionModeRequest(String posMode) {
    this.posMode = posMode;
  }

}
