package info.bitrich.xchangestream.okex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OkexPositionChangesMessage {
  /**
   * 产品类型
   */
  @JsonProperty("instType")
  private String instType;

  /**
   * 保证金模式，cross：全仓；isolated：逐仓
   */
  @JsonProperty("mgnMode")
  private String mgnMode;

  /**
   * 持仓ID
   */
  @JsonProperty("posId")
  private String posId;

  /**
   * 持仓方向： - long：开平仓模式开多 - short：开平仓模式开空 - net：买卖模式
   */
  @JsonProperty("posSide")
  private String posSide;

  /**
   * 持仓数量
   */
  @JsonProperty("pos")
  private String pos;

  /**
   * 交易币余额（已弃用）
   */
  @JsonProperty("baseBal")
  private String baseBal;

  /**
   * 计价币余额（已弃用）
   */
  @JsonProperty("quoteBal")
  private String quoteBal;

  /**
   * 交易币已借（已弃用）
   */
  @JsonProperty("baseBorrowed")
  private String baseBorrowed;

  /**
   * 交易币计息（已弃用）
   */
  @JsonProperty("baseInterest")
  private String baseInterest;

  /**
   * 计价币已借（已弃用）
   */
  @JsonProperty("quoteBorrowed")
  private String quoteBorrowed;

  /**
   * 计价币计息（已弃用）
   */
  @JsonProperty("quoteInterest")
  private String quoteInterest;

  /**
   * 持仓数量币种，仅适用于币币杠杆
   */
  @JsonProperty("posCcy")
  private String posCcy;

  /**
   * 可平仓数量
   */
  @JsonProperty("availPos")
  private String availPos;

  /**
   * 开仓平均价
   */
  @JsonProperty("avgPx")
  private String avgPx;

  /**
   * 未实现收益（以标记价格计算）
   */
  @JsonProperty("upl")
  private String upl;

  /**
   * 未实现收益率（以标记价格计算）
   */
  @JsonProperty("uplRatio")
  private String uplRatio;

  /**
   * 最新成交价格的未实现收益
   */
  @JsonProperty("uplLastPx")
  private String uplLastPx;

  /**
   * 最新成交价格的未实现收益率
   */
  @JsonProperty("uplRatioLastPx")
  private String uplRatioLastPx;

  /**
   * 产品ID，如 BTC-USDT-SWAP
   */
  @JsonProperty("instId")
  private String instId;

  /**
   * 杠杆倍数，不适用于期权卖方
   */
  @JsonProperty("lever")
  private String lever;

  /**
   * 预估强平价
   */
  @JsonProperty("liqPx")
  private String liqPx;

  /**
   * 最新标记价格
   */
  @JsonProperty("markPx")
  private String markPx;

  /**
   * 初始保证金，仅适用于全仓
   */
  @JsonProperty("imr")
  private String imr;

  /**
   * 保证金余额，仅适用于逐仓
   */
  @JsonProperty("margin")
  private String margin;

  /**
   * 保证金率
   */
  @JsonProperty("mgnRatio")
  private String mgnRatio;

  /**
   * 维持保证金
   */
  @JsonProperty("mmr")
  private String mmr;

  /**
   * 负债额，仅适用于币币杠杆
   */
  @JsonProperty("liab")
  private String liab;

  /**
   * 负债币种，仅适用于币币杠杆
   */
  @JsonProperty("liabCcy")
  private String liabCcy;

  /**
   * 利息
   */
  @JsonProperty("interest")
  private String interest;

  /**
   * 最新成交ID
   */
  @JsonProperty("tradeId")
  private String tradeId;

  /**
   * 持仓数量的美金价值
   */
  @JsonProperty("notionalUsd")
  private String notionalUsd;

  /**
   * 期权价值，仅适用于期权
   */
  @JsonProperty("optVal")
  private String optVal;

  /**
   * 逐仓杠杆负债对应平仓挂单的数量
   */
  @JsonProperty("pendingCloseOrdLiabVal")
  private String pendingCloseOrdLiabVal;

  /**
   * 信号区，分为1到5档
   */
  @JsonProperty("adl")
  private String adl;

  /**
   * 外部业务id
   */
  @JsonProperty("bizRefId")
  private String bizRefId;

  /**
   * 外部业务类型
   */
  @JsonProperty("bizRefType")
  private String bizRefType;

  /**
   * 占用保证金的币种
   */
  @JsonProperty("ccy")
  private String ccy;

  /**
   * 最新成交价
   */
  @JsonProperty("last")
  private String last;

  /**
   * 最新指数价格
   */
  @JsonProperty("idxPx")
  private String idxPx;

  /**
   * 盈亏平衡价
   */
  @JsonProperty("bePx")
  private String bePx;

  /**
   * 已实现收益
   */
  @JsonProperty("realizedPnl")
  private String realizedPnl;

  /**
   * 平仓订单累计收益额
   */
  @JsonProperty("pnl")
  private String pnl;

  /**
   * 累计手续费金额
   */
  @JsonProperty("fee")
  private String fee;

  /**
   * 累计资金费用
   */
  @JsonProperty("fundingFee")
  private String fundingFee;

  /**
   * 累计爆仓罚金
   */
  @JsonProperty("liqPenalty")
  private String liqPenalty;

  /**
   * 持仓创建时间
   */
  @JsonProperty("cTime")
  private String cTime;

  /**
   * 最近一次持仓更新时间
   */
  @JsonProperty("uTime")
  private String uTime;

  /**
   * 持仓信息的推送时间
   */
  @JsonProperty("pTime")
  private String pTime;
}

