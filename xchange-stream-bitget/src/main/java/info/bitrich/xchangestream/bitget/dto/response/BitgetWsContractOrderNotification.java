package info.bitrich.xchangestream.bitget.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.bitrich.xchangestream.bitget.dto.response.BitgetWsContractOrderNotification.OrderInfo;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@SuperBuilder(toBuilder = true)
@Jacksonized
public class BitgetWsContractOrderNotification extends BitgetWsNotification<OrderInfo> {

  @Data
  @Builder
  @Jacksonized
  public static class OrderInfo {

    /**
     * The order ID.
     */
    @JsonProperty("orderId")
    private String orderId;

    /**
     * Custom order ID.
     */
    @JsonProperty("clientOId")
    private String clientOid;

    /**
     * The order price.
     */
    @JsonProperty("price")
    private BigDecimal price;

    /**
     * The original order quantity, in base currency.
     */
    @JsonProperty("size")
    private BigDecimal size;

    /**
     * The position mode: one-way or hedge mode.
     */
    @JsonProperty("posMode")
    private String posMode;

    /**
     * The order source (origin of the order).
     */
    @JsonProperty("enterPointSource")
    private String enterPointSource;

    /**
     * The trading direction.
     */
    @JsonProperty("tradeSide")
    private String tradeSide;

    /**
     * The estimated notional USD value of the order.
     */
    @JsonProperty("notionalUsd")
    private String notionalUsd;

    /**
     * The type of order (limit or market).
     */
    @JsonProperty("orderType")
    private String orderType;

    /**
     * The order validity period (e.g., GTC, FOK, IOC).
     */
    @JsonProperty("force")
    private String force;

    /**
     * The order direction (buy or sell).
     */
    @JsonProperty("side")
    private String side;

    /**
     * The position direction in hedge mode.
     */
    @JsonProperty("posSide")
    private String posSide;

    /**
     * The margin mode (isolated or crossed).
     */
    @JsonProperty("marginMode")
    private String marginMode;

    /**
     * The margin currency.
     */
    @JsonProperty("marginCoin")
    private String marginCoin;

    /**
     * The latest fill price.
     */
    @JsonProperty("fillPrice")
    private BigDecimal fillPrice;

    /**
     * The latest trade ID.
     */
    @JsonProperty("tradeId")
    private String tradeId;

    /**
     * The latest filled volume in base currency.
     */
    @JsonProperty("baseVolume")
    private BigDecimal baseVolume;

    /**
     * The latest fill time in Unix milliseconds.
     */
    @JsonProperty("fillTime")
    private Long fillTime;

    /**
     * The fee for the latest trade (negative value).
     */
    @JsonProperty("fillFee")
    private BigDecimal fillFee;

    /**
     * The currency of the latest trade fee.
     */
    @JsonProperty("fillFeeCoin")
    private String fillFeeCoin;

    /**
     * The liquidity direction of the latest trade (Taker or Maker).
     */
    @JsonProperty("tradeScope")
    private String tradeScope;

    /**
     * The cumulative filled volume.
     */
    @JsonProperty("accBaseVolume")
    private BigDecimal accBaseVolume;

    /**
     * The notional USD value of the filled portion of the order.
     */
    @JsonProperty("fillNotionalUsd")
    private String fillNotionalUsd;

    /**
     * The average fill price.
     */
    @JsonProperty("priceAvg")
    private BigDecimal priceAvg;

    /**
     * The order status: live, partially_filled, filled, or canceled.
     */
    @JsonProperty("status")
    private String status;

    /**
     * The leverage applied to the order.
     */
    @JsonProperty("leverage")
    private Integer leverage;

    /**
     * Detailed fee information for the order.
     */
    @JsonProperty("feeDetail")
    private List<FeeDetail> feeDetail;

    /**
     * The profit or loss from the order.
     */
    @JsonProperty("pnl")
    private BigDecimal pnl;

    /**
     * The order update time in Unix milliseconds.
     */
    @JsonProperty("uTime")
    private Long uTime;

    /**
     * The order creation time in Unix milliseconds.
     */
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * Whether the order is reduce-only: yes or no.
     */
    @JsonProperty("reduceOnly")
    private String reduceOnly;

    /**
     * The preset take-profit price.
     */
    @JsonProperty("presetStopSurplusPrice")
    private BigDecimal presetStopSurplusPrice;

    /**
     * The preset stop-loss price.
     */
    @JsonProperty("presetStopLossPrice")
    private BigDecimal presetStopLossPrice;

    /**
     * The STP mode for preventing self-trades.
     */
    @JsonProperty("stpMode")
    private String stpMode;

    /**
     * The total profit or loss.
     */
    @JsonProperty("totalProfits")
    private String totalProfits;

    /**
     * Represents detailed information about fees for the order.
     */
    @Data
    public static class FeeDetail {

      /**
       * The currency in which the fee was charged.
       */
      @JsonProperty("feeCoin")
      private String feeCoin;

      /**
       * The fee amount charged by the platform.
       */
      @JsonProperty("fee")
      private BigDecimal fee;
    }
  }
}
