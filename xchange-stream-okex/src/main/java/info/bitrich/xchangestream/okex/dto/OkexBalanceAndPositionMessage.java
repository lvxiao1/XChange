package info.bitrich.xchangestream.okex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OkexBalanceAndPositionMessage {

  /**
   * 推送时间，Unix时间戳的毫秒数格式，例如 1597026383085
   */
  @JsonProperty("pTime")
  private String pTime;

  /**
   * 事件类型： - snapshot：首推快照 - delivered：交割 - exercised：行权 - transferred：划转 - filled：成交 -
   * liquidation：强平 - claw_back：穿仓补偿 - adl：ADL自动减仓 - funding_fee：资金费 - adjust_margin：调整保证金 -
   * set_leverage：设置杠杆 - interest_deduction：扣息
   */
  @JsonProperty("eventType")
  private String eventType;

  /**
   * 余额数据
   */
  @JsonProperty("balData")
  private List<BalanceData> balData;

  /**
   * 持仓数据
   */
  @JsonProperty("posData")
  private List<PositionData> posData;

  /**
   * 成交数据
   */
  @JsonProperty("trades")
  private List<TradeData> trades;

  /**
   * 余额数据子类
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class BalanceData {

    /**
     * 币种
     */
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 币种余额
     */
    @JsonProperty("cashBal")
    private String cashBal;

    /**
     * 币种余额信息的更新时间，Unix时间戳的毫秒数格式，例如 1597026383085
     */
    @JsonProperty("uTime")
    private String uTime;
  }

  /**
   * 持仓数据子类
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PositionData {

    /**
     * 持仓ID
     */
    @JsonProperty("posId")
    private String posId;

    /**
     * 最新成交ID
     */
    @JsonProperty("tradeId")
    private String tradeId;

    /**
     * 交易产品ID，例如 BTC-USD-180213
     */
    @JsonProperty("instId")
    private String instId;

    /**
     * 交易产品类型： - MARGIN：币币杠杆 - SWAP：永续合约 - FUTURES：交割合约 - OPTION：期权
     */
    @JsonProperty("instType")
    private String instType;

    /**
     * 保证金模式： - isolated：逐仓 - cross：全仓
     */
    @JsonProperty("mgnMode")
    private String mgnMode;

    /**
     * 开仓平均价
     */
    @JsonProperty("avgPx")
    private String avgPx;

    /**
     * 占用保证金的币种
     */
    @JsonProperty("ccy")
    private String ccy;

    /**
     * 持仓方向： - long：多头 - short：空头 - net：净头寸
     */
    @JsonProperty("posSide")
    private String posSide;

    /**
     * 持仓数量，逐仓自主划转模式下，转入保证金后会产生pos为0的仓位
     */
    @JsonProperty("pos")
    private String pos;

    /**
     * 持仓数量币种，仅适用于币币杠杆仓位
     */
    @JsonProperty("posCcy")
    private String posCcy;

    /**
     * 仓位信息的更新时间，Unix时间戳的毫秒数格式，例如 1597026383085
     */
    @JsonProperty("uTime")
    private String uTime;
  }

  /**
   * 成交数据子类
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TradeData {

    /**
     * 产品ID，例如 BTC-USDT
     */
    @JsonProperty("instId")
    private String instId;

    /**
     * 最新成交ID
     */
    @JsonProperty("tradeId")
    private String tradeId;
  }
}
