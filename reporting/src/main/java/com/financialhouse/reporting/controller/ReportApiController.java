package com.financialhouse.reporting.controller;

import com.financialhouse.reporting.dto.TransactionListRequestDTO;
import com.financialhouse.reporting.dto.TransactionListResponseDTO;
import com.financialhouse.reporting.dto.TransactionReportRequestDTO;
import com.financialhouse.reporting.dto.TransactionReportResponseDTO;
import com.financialhouse.reporting.externalApi.ExternalApiService;
import com.financialhouse.reporting.util.CommonConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = CommonConstants.MAIN_API)
public class ReportApiController {
    @Autowired
    ExternalApiService externalApiService;

    @PostMapping("/transactions/report")
    public Mono<ResponseEntity<TransactionReportResponseDTO>> getTransactionsReport(@RequestHeader(name = CommonConstants.AUTHORIZATION) String token, @RequestBody TransactionReportRequestDTO request) {
        return externalApiService.getTransactionsReport(request,token)
                .map(response -> ResponseEntity.ok().body(response))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @PostMapping("/transactions/list")
    public Mono<ResponseEntity<TransactionListResponseDTO>> getTransactionsList(@RequestHeader(name = CommonConstants.AUTHORIZATION) String token, @RequestBody TransactionListRequestDTO request) {
        return externalApiService.getTransactionsList(request,token)
               .map(response -> ResponseEntity.ok().body(response))
               .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

}
