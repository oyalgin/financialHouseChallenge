package com.financialhouse.reporting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class TransactionReportResponseDTO {
    @JsonProperty("status")
    private String status;

    @JsonProperty("response")
    private List<TransactionReportData> response;

    @Data
    public static class TransactionReportData {
        @JsonProperty("count")
        private int count;

        @JsonProperty("total")
        private int total;

        @JsonProperty("currency")
        private String currency;
    }
}

