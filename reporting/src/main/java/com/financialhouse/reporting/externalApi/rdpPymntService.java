package com.financialhouse.reporting.externalApi;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;
import com.financialhouse.reporting.dto.TransactionReportRequestDTO;
import com.financialhouse.reporting.dto.TransactionReportResponseDTO;
import com.financialhouse.reporting.util.TransactionsReportResponseMockData;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


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

        /**return webClient.post()
                .uri(baseUrl + transactionReportEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(TransactionReportResponseDTO.class);*/
    }

}
