package info.bitrich.xchangestream.bitget.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.bitrich.xchangestream.bitget.dto.response.BitGetWsContractPositionsNotification.Positions;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Represents a WebSocket notification for contract positions from BitGet.
 */
@Data
@SuperBuilder(toBuilder = true)
@Jacksonized
public class BitGetWsContractPositionsNotification extends BitgetWsNotification<Positions>{

  @Data
  @Builder
  @Jacksonized
  public static class Positions {

    /**
     * Position ID
     */
    @JsonProperty("posId")
    private String posId;

    /**
     * Instrument ID, refer to: https://www.bitget.com/zh-CN/api-doc/common/release-note
     */
    @JsonProperty("instId")
    private String instId;

    /**
     * The currency of the margin used
     */
    @JsonProperty("marginCoin")
    private String marginCoin;

    /**
     * The amount of margin used
     */
    @JsonProperty("marginSize")
    private String marginSize;

    /**
     * Margin mode
     */
    @JsonProperty("marginMode")
    private String marginMode;

    /**
     * Position direction
     */
    @JsonProperty("holdSide")
    private String holdSide;

    /**
     * Position mode
     */
    @JsonProperty("posMode")
    private String posMode;

    /**
     * Total position size
     */
    @JsonProperty("total")
    private BigDecimal total;

    /**
     * Available size for closing
     */
    @JsonProperty("available")
    private BigDecimal available;

    /**
     * Frozen amount
     */
    @JsonProperty("frozen")
    private BigDecimal frozen;

    /**
     * Average opening price
     */
    @JsonProperty("openPriceAvg")
    private BigDecimal openPriceAvg;

    /**
     * Leverage
     */
    @JsonProperty("leverage")
    private Integer leverage;

    /**
     * Achieved profits
     */
    @JsonProperty("achievedProfits")
    private String achievedProfits;

    /**
     * Unrealized profits and losses
     */
    @JsonProperty("unrealizedPL")
    private BigDecimal unrealizedPL;

    /**
     * Unrealized profit and loss ratio
     */
    @JsonProperty("unrealizedPLR")
    private BigDecimal unrealizedPLR;

    /**
     * Estimated liquidation price
     */
    @JsonProperty("liquidationPrice")
    private BigDecimal liquidationPrice;

    /**
     * Maintenance margin rate
     */
    @JsonProperty("keepMarginRate")
    private BigDecimal keepMarginRate;

    /**
     * Actual margin rate when isolating
     */
    @JsonProperty("isolatedMarginRate")
    private BigDecimal isolatedMarginRate;

    /**
     * Margin occupancy rate
     */
    @JsonProperty("marginRate")
    private BigDecimal marginRate;

    /**
     * Breakeven price
     */
    @JsonProperty("breakEvenPrice")
    private BigDecimal breakEvenPrice;

    /**
     * Cumulative funding fees, initially empty if no funding fee is charged yet
     */
    @JsonProperty("totalFee")
    private BigDecimal totalFee;

    /**
     * Deducted fee during the life of the position
     */
    @JsonProperty("deductedFee")
    private BigDecimal deductedFee;

    /**
     * Position creation time in Unix timestamp format (milliseconds)
     */
    @JsonProperty("cTime")
    private Long cTime;

    /**
     * Last updated time of the position in Unix timestamp format (milliseconds)
     */
    @JsonProperty("uTime")
    private Long uTime;
  }

}
