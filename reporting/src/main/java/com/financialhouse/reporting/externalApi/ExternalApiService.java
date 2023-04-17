package com.financialhouse.reporting.externalApi;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;
import com.financialhouse.reporting.dto.TransactionReportRequestDTO;
import com.financialhouse.reporting.dto.TransactionReportResponseDTO;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ExternalApiService {

    Mono<LoginResponseDTO> userLogin(LoginRequestDTO request);

    Mono<TransactionReportResponseDTO> getTransactionsReport(TransactionReportRequestDTO request, String token);
}
