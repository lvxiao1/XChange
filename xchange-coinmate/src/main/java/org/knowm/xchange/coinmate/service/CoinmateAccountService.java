/*
 * The MIT License
 *
 * Copyright 2015 Coinmate.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.knowm.xchange.coinmate.service;

import static org.apache.commons.lang3.math.NumberUtils.toLong;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.coinmate.CoinmateAdapters;
import org.knowm.xchange.coinmate.CoinmateUtils;
import org.knowm.xchange.coinmate.dto.account.AmountType;
import org.knowm.xchange.coinmate.dto.account.CoinmateDepositAddresses;
import org.knowm.xchange.coinmate.dto.account.CoinmateTradingFeesResponseData;
import org.knowm.xchange.coinmate.dto.account.FeePriority;
import org.knowm.xchange.coinmate.dto.trade.CoinmateTradeResponse;
import org.knowm.xchange.coinmate.dto.trade.CoinmateTransactionHistory;
import org.knowm.xchange.coinmate.dto.trade.CoinmateTransferDetail;
import org.knowm.xchange.coinmate.dto.trade.CoinmateTransferHistory;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.AddressWithTag;
import org.knowm.xchange.dto.account.Fee;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.DefaultWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamLimit;
import org.knowm.xchange.service.trade.params.TradeHistoryParamOffset;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsIdSpan;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsSorted;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

/**
 * @author Martin Stachon
 */
public class CoinmateAccountService extends CoinmateAccountServiceRaw implements AccountService {

  public static final int DEFAULT_RESULT_LIMIT = 100;

  public CoinmateAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    return new AccountInfo(CoinmateAdapters.adaptWallet(getCoinmateBalance()));
  }

  @Override
  public Map<Instrument, Fee> getDynamicTradingFeesByInstrument() throws IOException {
    Set<Instrument> instruments = exchange.getExchangeMetaData().getInstruments().keySet();
    HashMap<Instrument, Fee> result = new HashMap<>();
    for (Instrument instrument : instruments) {
      CoinmateTradingFeesResponseData data =
          getCoinmateTraderFees(CoinmateUtils.getPair(instrument));
      Fee fee = new Fee(data.getMaker(), data.getTaker());
      result.put(instrument, fee);
    }
    return result;
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address)
      throws IOException {
    CoinmateTradeResponse response;

    if (currency.equals(Currency.BTC)) {
      response = coinmateBitcoinWithdrawal(amount, address);
    } else if (currency.equals(Currency.LTC)) {
      response = coinmateLitecoinWithdrawal(amount, address);
    } else if (currency.equals(Currency.ETH)) {
      response = coinmateEthereumWithdrawal(amount, address);
    } else if (currency.equals(Currency.XRP)) {
      response = coinmateRippleWithdrawal(amount, address);
    } else if (currency.equals(Currency.ADA)) {
      response = coinmateCardanoWithdrawal(amount, address);
    } else if (currency.equals(Currency.SOL)) {
      response = coinmateSolanaWithdrawal(amount, address);
    } else if (currency.equals(Currency.USDT)) {
      Long tradeId =
          coinmateWithdrawVirtualCurrency(
              amount,
              address,
              Currency.USDT.getCurrencyCode(),
              AmountType.GROSS,
              FeePriority.HIGH,
              null);
      return Long.toString(tradeId);
    } else {
      throw new IOException(
          "Wallet for currency" + currency.getCurrencyCode() + " is currently not supported");
    }

    return Long.toString(response.getData());
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, AddressWithTag address)
      throws IOException {
    if (currency.equals(Currency.XRP)) {
      Long tradeId =
          coinmateWithdrawVirtualCurrency(
              amount,
              address.getAddress(),
              currency.getCurrencyCode(),
              AmountType.GROSS,
              FeePriority.HIGH,
              address.getAddressTag());
      return Long.toString(tradeId);
    } else {
      return withdrawFunds(currency, amount, address.getAddress());
    }
  }

  @Override
  public String withdrawFunds(WithdrawFundsParams params) throws IOException {
    if (params instanceof DefaultWithdrawFundsParams) {
      DefaultWithdrawFundsParams defaultParams = (DefaultWithdrawFundsParams) params;

      if (defaultParams.getCurrency().equals(Currency.XRP)) {
        Long tradeId =
            coinmateWithdrawVirtualCurrency(
                defaultParams.getAmount(),
                defaultParams.getAddress(),
                defaultParams.getCurrency().getCurrencyCode(),
                AmountType.GROSS,
                FeePriority.HIGH,
                defaultParams.getAddressTag());
        return Long.toString(tradeId);
      }

      return withdrawFunds(
          defaultParams.getCurrency(), defaultParams.getAmount(), defaultParams.getAddress());
    }
    throw new IllegalStateException("Don't know how to withdraw: " + params);
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args) throws IOException {
    CoinmateDepositAddresses addresses;
    if (currency.equals(Currency.BTC)) {
      addresses = coinmateBitcoinDepositAddresses();
    } else if (currency.equals(Currency.LTC)) {
      addresses = coinmateLitecoinDepositAddresses();
    } else if (currency.equals(Currency.ETH)) {
      addresses = coinmateEthereumDepositAddresses();
    } else if (currency.equals(Currency.XRP)) {
      addresses = coinmateRippleDepositAddresses();
    } else if (currency.equals(Currency.ADA)) {
      addresses = coinmateCardanoDepositAddresses();
    } else if (currency.equals(Currency.SOL)) {
      addresses = coinmateSolanaDepositAddresses();
    } else if (currency.equals(Currency.USDT)) {
      List<String> addressesAll =
          coinmateVirtualCurrencyDepositAddresses(currency.getCurrencyCode());
      if (addressesAll.isEmpty()) {
        return null;
      }
      return addressesAll.get(0);
    } else {
      throw new IOException(
          "Wallet for currency" + currency.getCurrencyCode() + " is currently not supported");
    }

    if (addresses.getData().isEmpty()) {
      return null;
    } else {
      // here we return only the first address
      return addresses.getData().get(0);
    }
  }

  @Override
  public TradeHistoryParams createFundingHistoryParams() {
    return new CoinmateFundingHistoryParams();
  }

  @Override
  public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws IOException {

    TradeHistoryParamsSorted.Order order = TradeHistoryParamsSorted.Order.asc;
    Integer limit = 1000;
    int offset = 0;
    Long timestampFrom = null;
    Long timestampTo = null;

    if (params instanceof TradeHistoryParamsIdSpan) {
      String transactionId = ((TradeHistoryParamsIdSpan) params).getStartId();
      if (transactionId != null) {
        CoinmateTransferDetail coinmateTransferDetail =
            getCoinmateTransferDetail(toLong(transactionId));
        return CoinmateAdapters.adaptFundingDetail(coinmateTransferDetail);
      }
    }

    if (params instanceof TradeHistoryParamOffset) {
      offset = Math.toIntExact(((TradeHistoryParamOffset) params).getOffset());
    }

    if (params instanceof TradeHistoryParamLimit) {
      limit = ((TradeHistoryParamLimit) params).getLimit();
    }

    if (params instanceof TradeHistoryParamsSorted) {
      order = ((TradeHistoryParamsSorted) params).getOrder();
    }

    if (params instanceof TradeHistoryParamsTimeSpan) {
      TradeHistoryParamsTimeSpan thpts = (TradeHistoryParamsTimeSpan) params;
      if (thpts.getStartTime() != null) {
        timestampFrom = thpts.getStartTime().getTime();
      }
      if (thpts.getEndTime() != null) {
        timestampTo = thpts.getEndTime().getTime();
      }
    }
    CoinmateTransferHistory coinmateTransferHistory =
        getTransfersData(limit, timestampFrom, timestampTo);

    CoinmateTransactionHistory coinmateTransactionHistory =
        getCoinmateTransactionHistory(
            offset,
            limit,
            CoinmateAdapters.adaptSortOrder(order),
            timestampFrom,
            timestampTo,
            null);
    return CoinmateAdapters.adaptFundingHistory(
        coinmateTransactionHistory, coinmateTransferHistory);
  }

  public static class CoinmateFundingHistoryParams
      extends CoinmateTradeService.CoinmateTradeHistoryHistoryParams {}
}
