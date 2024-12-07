package info.bitrich.xchangestream.bitget.dto.response;

import info.bitrich.xchangestream.bitget.dto.response.BitgetWsContractOrderBookNotification.OrderBookData;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@SuperBuilder(toBuilder = true)
@Jacksonized
public class BitgetWsContractOrderBookNotification extends BitgetWsNotification<OrderBookData> {

  @Data
  @Builder
  @Jacksonized
  public static class OrderBookData {

    private List<List<BigDecimal>> bids;
    private List<List<BigDecimal>> asks;
    private Long checksum;
    private Long ts;
  }
}
