package info.bitrich.xchangestream.okex.dto;

import java.util.List;
import lombok.*;
import org.knowm.xchange.okex.dto.OkexInstType;

@Data
@AllArgsConstructor
public class OkexSubscribeMessage {
  private final String op;
  private final List<SubscriptionTopic> args;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SubscriptionTopic {
    private String channel;

    private OkexInstType instType;

    private String uly;

    private String instId;

    private String instFamily;

    public SubscriptionTopic(String channel, OkexInstType instType, String uly, String instId) {
      this.channel = channel;
      this.instType = instType;
      this.uly = uly;
      this.instId = instId;
    }

    public SubscriptionTopic(String channel) {
      this.channel = channel;
    }
  }
}
