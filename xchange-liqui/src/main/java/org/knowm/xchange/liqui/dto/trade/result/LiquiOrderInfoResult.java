package org.knowm.xchange.liqui.dto.trade.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.liqui.dto.LiquiStat;
import org.knowm.xchange.liqui.dto.marketdata.LiquiResult;
import org.knowm.xchange.liqui.dto.trade.LiquiOrderInfo;

import java.util.Map;

public class LiquiOrderInfoResult extends LiquiResult<Map<Long, LiquiOrderInfo>> {

    private final boolean success;
    private final LiquiStat stat;

    public LiquiOrderInfoResult(@JsonProperty("success") final boolean success,
                                @JsonProperty("return") final Map<Long, LiquiOrderInfo> result,
                                @JsonProperty("stat") final LiquiStat stat) {
        super(result);
        this.success = success;
        this.stat = stat;
    }

    public boolean isSuccess() {
        return success;
    }

    public LiquiStat getStat() {
        return stat;
    }

    @Override
    public String toString() {
        return "LiquiActiveOrdersResult{" +
                "success=" + success +
                ", stat=" + stat +
                '}';
    }
}
