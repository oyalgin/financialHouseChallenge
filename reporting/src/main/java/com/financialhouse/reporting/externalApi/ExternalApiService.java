package com.financialhouse.reporting.externalApi;

import com.financialhouse.reporting.dto.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ExternalApiService {

    Mono<LoginResponseDTO> userLogin(LoginRequestDTO request);

    Mono<TransactionReportResponseDTO> getTransactionsReport(TransactionReportRequestDTO request, String token);

    Mono<TransactionListResponseDTO> getTransactionsList(TransactionListRequestDTO request, String token);

}
