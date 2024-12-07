package info.bitrich.xchangestream.bitget.dto.response;


import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.knowm.xchange.bitget.dto.marketdata.MarketContractTradeDto;

@Data
@SuperBuilder(toBuilder = true)
@Jacksonized
public class BitgetWsContractTradesNotification extends BitgetWsNotification<MarketContractTradeDto> {

}
