package org.knowm.xchange.bitget;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
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

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface Bitget {

  @GET
  @Path("api/v2/public/time")
  BitgetResponse<BitgetServerTime> serverTime() throws IOException, BitgetException;

  @GET
  @Path("api/v2/spot/public/coins")
  BitgetResponse<List<BitgetCoinDto>> coins(@QueryParam("coin") String coin)
      throws IOException, BitgetException;

  @GET
  @Path("api/v2/spot/public/symbols")
  BitgetResponse<List<BitgetSymbolDto>> symbols(@QueryParam("symbol") String symbol)
      throws IOException, BitgetException;

  @GET
  @Path("api/v2/spot/market/tickers")
  BitgetResponse<List<BitgetTickerDto>> tickers(@QueryParam("symbol") String symbol)
      throws IOException, BitgetException;

  @GET
  @Path("api/v2/spot/market/orderbook")
  BitgetResponse<BitgetMarketDepthDto> orderbook(@QueryParam("symbol") String symbol)
      throws IOException, BitgetException;

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
  @GET
  @Path("api/v2/mix/market/contracts")
  BitgetResponse<List<BitgetContractDto>> contracts(@QueryParam("productType") String productType, @QueryParam("symbol") String symbol)
      throws IOException, BitgetException;

  @GET
  @Path("api/v2/mix/market/ticker")
  BitgetResponse<List<BitgetContractTickerDto>> contractTickers(@QueryParam("productType") String productType, @QueryParam("symbol") String symbol)
      throws IOException, BitgetException;

  @GET
  @Path("api/v2/mix/market/current-fund-rate")
  BitgetResponse<List<BitgetContractFundRateDto>> currentContractFundRate(@QueryParam("productType") String productType, @QueryParam("symbol") String symbol)
      throws IOException, BitgetException;

  @GET
  @Path("api/v2/mix/market/funding-time")
  BitgetResponse<List<BitgetContractFundRateTimeDto>> contractFundRateTime(@QueryParam("productType") String productType, @QueryParam("symbol") String symbol)
      throws IOException, BitgetException;

}
