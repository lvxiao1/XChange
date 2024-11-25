package org.knowm.xchange.okex.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OkexSetPositionModeResponse {
  @JsonProperty("posMode")
  private String posMode;
}
