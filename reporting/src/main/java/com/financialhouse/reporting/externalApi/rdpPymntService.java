package com.financialhouse.reporting.externalApi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.financialhouse.reporting.dto.*;
import com.financialhouse.reporting.util.TransactionsReportResponseMockData;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class rdpPymntService implements ExternalApiService{

    @Autowired
    private WebClient webClient;

    @Value("${rdpPymnt.baseUrl}")
    private String baseUrl;

    @Value("${rdpPymnt.serviceEndpoints.login}")
    private String loginEndpoint;

    @Value("${rdpPymnt.serviceEndpoints.transactionReport}")
    private String transactionReportEndpoint;

    @Value("${rdpPymnt.serviceEndpoints.transactionsList}")
    private String transactionListEndpoint;

    @Value("${rdpPymnt.serviceEndpoints.transaction}")
    private String transaction;

    @Value("${rdpPymnt.serviceEndpoints.client}")
    private String client;

    @Override
    public Mono<LoginResponseDTO> userLogin(LoginRequestDTO request) {
        return webClient.post()
                .uri(baseUrl+ loginEndpoint)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(LoginResponseDTO.class);

    }

    @Override
    public Mono<TransactionReportResponseDTO> getTransactionsReport(TransactionReportRequestDTO request, String token) {

       return Mono.fromSupplier(TransactionsReportResponseMockData::createMockData);

       /** return webClient.post()
                .uri(baseUrl + transactionReportEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(TransactionReportResponseDTO.class);*/
    }

    @Override
    public Mono<TransactionListResponseDTO> getTransactionsList(TransactionListRequestDTO request, String token) {

        return webClient.post()
                .uri(baseUrl + transactionListEndpoint)
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TransactionListResponseDTO.class);

    }

    @Override
    public Mono<TransactionResponseDTO> getTransactionDetail(TransactionRequestDTO request, String token) {
        return webClient.post()
                .uri(baseUrl + transaction)
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TransactionResponseDTO.class);
    }

    @Override
    public Mono<ClientResponseDTO> getClient(TransactionRequestDTO request, String token) {
        return webClient.post()
                .uri(baseUrl + client)
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ClientResponseDTO.class);
    }
}
