package info.bitrich.xchangestream.okex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OkexOrderBookMessage {

  /**
   * Action of the push data, indicating whether the data is incremental or full. Possible values: -
   * snapshot: Full data push - update: Incremental data push
   */
  @JsonProperty("action")
  private String action;

  /**
   * List of data items containing market depth details.
   */
  @JsonProperty("data")
  private List<DataItem> data;

  /**
   * Represents a single data item in the push data.
   */
  @Data
  public static class DataItem {

    /**
     * List representing the sell-side depth (asks).
     */
    @JsonProperty("asks")
    private List<List<String>> asks;

    /**
     * List representing the buy-side depth (bids).
     */
    @JsonProperty("bids")
    private List<List<String>> bids;

    /**
     * Data update timestamp in Unix milliseconds format, e.g., 1597026383085.
     */
    @JsonProperty("ts")
    private String timestamp;

    /**
     * Checksum value for data validation.
     */
    @JsonProperty("checksum")
    private Long checksum;

    /**
     * Sequence ID of the previous push. Only applicable to books, books-l2-tbt, and
     * books50-l2-tbt.
     */
    @JsonProperty("prevSeqId")
    private Long previousSequenceId;

    /**
     * Sequence ID of the current push.
     */
    @JsonProperty("seqId")
    private Long sequenceId;
  }
}
