package org.knowm.xchange.bitget.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import java.io.IOException;
import java.util.List;
import org.knowm.xchange.bitget.BitgetAdapters;
import org.knowm.xchange.bitget.BitgetExchange;
import org.knowm.xchange.bitget.dto.BitgetException;
import org.knowm.xchange.bitget.dto.BitgetResponse;
import org.knowm.xchange.bitget.dto.marketdata.BitgetCoinDto;
import org.knowm.xchange.bitget.dto.marketdata.BitgetContractDto;
import org.knowm.xchange.bitget.dto.marketdata.BitgetContractFundRateDto;
import org.knowm.xchange.bitget.dto.marketdata.BitgetContractFundRateTimeDto;
import org.knowm.xchange.bitget.dto.marketdata.BitgetContractTickerDto;
import org.knowm.xchange.bitget.dto.marketdata.BitgetMarketDepthDto;
import org.knowm.xchange.bitget.dto.marketdata.BitgetServerTime;
import org.knowm.xchange.bitget.dto.marketdata.BitgetSymbolDto;
import org.knowm.xchange.bitget.dto.marketdata.BitgetTickerDto;
import org.knowm.xchange.bitget.dto.marketdata.MarketContractTradeDto;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.instrument.Instrument;

public class BitgetMarketDataServiceRaw extends BitgetBaseService {

  public BitgetMarketDataServiceRaw(BitgetExchange exchange) {
    super(exchange);
  }

  public BitgetServerTime getBitgetServerTime() throws IOException {
    return bitget.serverTime().getData();
  }

  public List<BitgetCoinDto> getBitgetCoinDtoList(Currency currency) throws IOException {
    return bitget.coins(BitgetAdapters.toString(currency)).getData();
  }

  public List<BitgetSymbolDto> getBitgetSymbolDtos(Instrument instrument) throws IOException {
    return bitget.symbols(BitgetAdapters.toString(instrument)).getData();
  }

  public List<BitgetTickerDto> getBitgetTickerDtos(Instrument instrument) throws IOException {
    return bitget.tickers(BitgetAdapters.toString(instrument)).getData();
  }

  public BitgetMarketDepthDto getBitgetMarketDepthDtos(Instrument instrument) throws IOException {
    return bitget.orderbook(BitgetAdapters.toString(instrument)).getData();
  }

  /**
   * @param productType
   * Product type, indicating the type of futures contract:
   * <ul>
   *   <li>USDT-FUTURES - USDT perpetual futures</li>
   *   <li>COIN-FUTURES - Coin-margined futures</li>
   *   <li>USDC-FUTURES - USDC perpetual futures</li>
   *   <li>SUSDT-FUTURES - Simulated USDT perpetual futures</li>
   *   <li>SCOIN-FUTURES - Simulated coin-margined futures</li>
   *   <li>SUSDC-FUTURES - Simulated USDC perpetual futures</li>
   * </ul>
   */
  public List<BitgetContractDto> contracts(String productType, String symbol)
      throws IOException{
    return bitget.contracts(productType, symbol).getData();
  }

  public List<BitgetContractTickerDto> contractTickers(String productType, String symbol)
      throws IOException{
    return bitget.contractTickers(productType, symbol).getData();
  }

  public List<BitgetContractFundRateDto> currentContractFundRate(String productType, String symbol)
      throws IOException {
    return bitget.currentContractFundRate(productType, symbol).getData();
  }

  public List<BitgetContractFundRateTimeDto> contractFundRateTime(String productType, String symbol)
      throws IOException{
    return bitget.contractFundRateTime(productType, symbol).getData();
  }

  public List<MarketContractTradeDto> contractFillsHistory(
      String productType,
      String symbol,
      Integer limit,
      String idLessThan,
      Long startTime,
      Long endTime) throws IOException{
    return bitget.contractFillsHistory(productType, symbol, limit, idLessThan, startTime,endTime).getData();
  }
}
