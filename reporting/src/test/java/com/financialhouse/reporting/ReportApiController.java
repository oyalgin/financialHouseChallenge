package com.financialhouse.reporting;

import com.financialhouse.reporting.dto.TransactionReportRequestDTO;
import com.financialhouse.reporting.dto.TransactionReportResponseDTO;
import com.financialhouse.reporting.util.CommonConstants;
import com.financialhouse.reporting.util.TransactionsReportResponseMockData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
public class ReportApiController extends BaseTest{

    @Test
    @DisplayName("Should return mocked response when a valid request provides")
    public void shouldReturnMockedResponseWhenValidRequestAndTokenProvided() {
        // given
        TransactionReportRequestDTO request = TransactionReportRequestDTO.builder()
                .fromDate(LocalDate.of(2015, 7, 1))
                .toDate(LocalDate.of(2015, 10, 1))
                .merchant(1)
                .acquirer(1)
                .build();

        String token = "test-token";

        TransactionReportResponseDTO response = TransactionsReportResponseMockData.createMockData();

        when(externalApiService.getTransactionsReport(Mockito.any(TransactionReportRequestDTO.class), Mockito.anyString()))
                .thenReturn(Mono.just(response));
        //when
        getWebTestClient().post()
                .uri("/api/transactions/report")
                .header(CommonConstants.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(BodyInserters.fromValue(request))
                .exchange()
        //then
                .expectStatus().isOk()
                .expectBody(TransactionReportResponseDTO.class)
                .isEqualTo(response);
    }
}
