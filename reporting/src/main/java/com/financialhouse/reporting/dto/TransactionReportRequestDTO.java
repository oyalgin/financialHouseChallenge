package com.financialhouse.reporting.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class TransactionReportRequestDTO {
    @JsonProperty("fromDate")
    @NotBlank
    @NotNull
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate fromDate;

    @JsonProperty("toDate")
    @NotBlank
    @NotNull
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate toDate;

    @JsonProperty("merchant")
    private Integer merchant;

    @JsonProperty("acquirer")
    private Integer acquirer;
}
