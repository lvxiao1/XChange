package org.knowm.xchange.gateio;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import org.knowm.xchange.gateio.dto.GateioException;
import org.knowm.xchange.gateio.dto.account.BatchFuturesOrderResponse;
import org.knowm.xchange.gateio.dto.account.FuturesOrder;
import org.knowm.xchange.gateio.dto.account.GateioAccountBookRecord;
import org.knowm.xchange.gateio.dto.account.GateioAddressRecord;
import org.knowm.xchange.gateio.dto.account.GateioCurrencyBalance;
import org.knowm.xchange.gateio.dto.account.GateioDepositAddress;
import org.knowm.xchange.gateio.dto.account.GateioDepositRecord;
import org.knowm.xchange.gateio.dto.account.GateioFuturesAccountBookRecord;
import org.knowm.xchange.gateio.dto.account.GateioOrder;
import org.knowm.xchange.gateio.dto.account.GateioSubAccountTransfer;
import org.knowm.xchange.gateio.dto.account.GateioWithdrawStatus;
import org.knowm.xchange.gateio.dto.account.GateioWithdrawalRecord;
import org.knowm.xchange.gateio.dto.account.GateioWithdrawalRequest;
import org.knowm.xchange.gateio.dto.trade.GateioUserTradeRaw;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("api/v4")
@Produces(MediaType.APPLICATION_JSON)
public interface GateioV4Authenticated {

  @GET
  @Path("wallet/deposit_address")
  GateioDepositAddress getDepositAddress(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency") String currency)
      throws IOException, GateioException;

  @GET
  @Path("wallet/withdraw_status")
  List<GateioWithdrawStatus> getWithdrawStatus(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency") String currency)
      throws IOException, GateioException;

  @GET
  @Path("spot/accounts")
  List<GateioCurrencyBalance> getSpotAccounts(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency") String currency)
      throws IOException, GateioException;

  @GET
  @Path("spot/account_book")
  List<GateioAccountBookRecord> getAccountBookRecords(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency") String currency,
      @QueryParam("from") Long from,
      @QueryParam("to") Long to,
      @QueryParam("limit") Integer pageLength,
      @QueryParam("page") Integer pageNumber,
      @QueryParam("type") String type)
      throws IOException, GateioException;

  @GET
  @Path("spot/orders")
  List<GateioOrder> listOrders(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency_pair") String currencyPair,
      @QueryParam("status") String status)
      throws IOException, GateioException;

  @GET
  @Path("spot/orders/{order_id}")
  GateioOrder getOrder(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @PathParam("order_id") String orderId,
      @QueryParam("currency_pair") String currencyPair)
      throws IOException, GateioException;

  @DELETE
  @Path("spot/orders/{order_id}")
  GateioOrder cancelOrder(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @PathParam("order_id") String orderId,
      @QueryParam("currency_pair") String currencyPair)
      throws IOException, GateioException;

  @POST
  @Path("spot/orders")
  @Consumes(MediaType.APPLICATION_JSON)
  GateioOrder createOrder(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      GateioOrder gateioOrder)
      throws IOException, GateioException;

  @GET
  @Path("spot/my_trades")
  List<GateioUserTradeRaw> getTradingHistory(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency_pair") String currencyPair,
      @QueryParam("limit") Integer pageLength,
      @QueryParam("page") Integer pageNumber,
      @QueryParam("order_id") String orderId,
      @QueryParam("account") String account,
      @QueryParam("from") Long from,
      @QueryParam("to") Long to)
      throws IOException, GateioException;

  @GET
  @Path("wallet/saved_address")
  List<GateioAddressRecord> getSavedAddresses(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency") String currency)
      throws IOException, GateioException;

  @GET
  @Path("wallet/sub_account_transfers")
  List<GateioSubAccountTransfer> getSubAccountTransfers(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("sub_uid") String subAccountId,
      @QueryParam("from") Long from,
      @QueryParam("to") Long to,
      @QueryParam("limit") Integer pageLength,
      @QueryParam("offset") Integer zeroBasedPageNumber)
      throws IOException, GateioException;

  @GET
  @Path("wallet/withdrawals")
  List<GateioWithdrawalRecord> getWithdrawals(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency") String currency,
      @QueryParam("from") Long from,
      @QueryParam("to") Long to,
      @QueryParam("limit") Integer pageLength,
      @QueryParam("offset") Integer zeroBasedPageNumber)
      throws IOException, GateioException;

  @GET
  @Path("wallet/deposits")
  List<GateioDepositRecord> getDeposits(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @QueryParam("currency") String currency,
      @QueryParam("from") Long from,
      @QueryParam("to") Long to,
      @QueryParam("limit") Integer pageLength,
      @QueryParam("offset") Integer zeroBasedPageNumber)
      throws IOException, GateioException;

  @POST
  @Path("withdrawals")
  @Consumes(MediaType.APPLICATION_JSON)
  GateioWithdrawalRecord withdraw(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      GateioWithdrawalRequest gateioWithdrawalRequest)
      throws IOException, GateioException;

  @POST
  @Path("/futures/{settle}/orders")
  @Consumes(MediaType.APPLICATION_JSON)
  FuturesOrder createFuturesOrder(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @PathParam("settle") String settle,
      FuturesOrder futuresOrder)
      throws IOException, GateioException;

  @POST
  @Path("/futures/{settle}/batch_orders")
  @Consumes(MediaType.APPLICATION_JSON)
  List<BatchFuturesOrderResponse> createBatchFuturesOrder(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @PathParam("settle") String settle,
      List<FuturesOrder> futuresOrders)
      throws IOException, GateioException;

  @GET
  @Path("/futures/{settle}/orders/{order_id}")
  FuturesOrder getFuturesOrder(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @PathParam("settle") String settle,
      @PathParam("order_id") String orderId)
      throws IOException, GateioException;

  @DELETE
  @Path("/futures/{settle}/orders/{order_id}")
  FuturesOrder cancelFuturesOrder(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @PathParam("settle") String settle,
      @PathParam("order_id") String orderId)
      throws IOException, GateioException;

  /**
   * @param type
   * dnw: Deposit and withdrawal
   * pnl: Position profit and loss
   * fee: Trading fee
   * refr: Referrer rebate
   * fund: Funding fee
   * point_dnw: Point card deposit and withdrawal
   * point_fee: Point card trading fee
   * point_refr: Point card referrer rebate
   * bonus_offset: Bonus deduction
   * @return
   * @throws IOException
   * @throws GateioException
   */
  @GET
  @Path("/futures/{settle}/account_book")
  List<GateioFuturesAccountBookRecord> getFuturesAccountBookRecords(
      @HeaderParam("KEY") String apiKey,
      @HeaderParam("Timestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("SIGN") ParamsDigest signer,
      @PathParam("settle") String settle,
      @QueryParam("contract") String contract,
      @QueryParam("type") String type,
      @QueryParam("limit") int limit,
      @QueryParam("offset") int offset,
      @QueryParam("from") Long fromTimestamp,
      @QueryParam("to") Long toTimestamp
  ) throws IOException, GateioException;

}
