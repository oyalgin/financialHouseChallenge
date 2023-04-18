package com.financialhouse.reporting;

import com.financialhouse.reporting.dto.*;
import com.financialhouse.reporting.util.CommonConstants;
import com.financialhouse.reporting.util.TransactionsReportResponseMockData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
public class ReportApiController extends BaseTest{

    @Test
    @DisplayName("Should return transaction report when /api/transactions/report endpoint is requested with valid token and body")
    public void shouldReturn_MockedResponse_When_ValidRequestAndTokenProvided() {
        // given
        // Transaction report request
        TransactionReportRequestDTO request = TransactionReportRequestDTO.builder()
                .fromDate(LocalDate.of(2015, 7, 1))
                .toDate(LocalDate.of(2015, 10, 1))
                .merchant(1)
                .acquirer(1)
                .build();

        String token = "test-token";

        // Transaction report response
        TransactionReportResponseDTO response = TransactionsReportResponseMockData.createMockData();
        //mock
        when(externalApiService.getTransactionsReport(Mockito.any(TransactionReportRequestDTO.class), anyString()))
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

    @Test
    @DisplayName("Should return Client customer information when /api/client endpoint is requested with valid trancactionId")
    public void shouldReturn_MockedClientResponseDTO_WhenExternalApiServiceReturnsSuccess() {
        // Given
        String token = "test-token";
        TransactionRequestDTO request = new TransactionRequestDTO();
        request.setTransactionId("test-transaction-id");

        ClientResponseDTO.CustomerInfo customerInfo = new ClientResponseDTO.CustomerInfo();
        customerInfo.setBillingFirstName("John");
        customerInfo.setBillingLastName("Doe");
        customerInfo.setIssueNumber("123");
        customerInfo.setEmail("john.doe@example.com");
        customerInfo.setBillingCompany("Acme Inc");
        customerInfo.setBillingCity("New York");
        customerInfo.setUpdated_at("2022-01-01T00:00:00Z");
        customerInfo.setCreated_at("2022-01-01T00:00:00Z");
        customerInfo.setId(1);

        ClientResponseDTO response = new ClientResponseDTO();
        response.setCustomerInfo(customerInfo);

        when(externalApiService.getClient(request,token))
                .thenReturn(Mono.just(response));

        // when
        getWebTestClient().post().uri("/api/client")
                .header(CommonConstants.AUTHORIZATION, token)
                .bodyValue(request)
                .exchange()
       //then
                .expectStatus().isOk()
                .expectBody(ClientResponseDTO.class)
                .isEqualTo(response);
    }

    @Test
    @DisplayName("Should return internal server errror when external client returns not expected result and /api/client endpoint is requested w")
    public void getClient_ReturnsInternalServerError_WhenExternalApiServiceReturnsError() {
        // Given
        String token = "test-token";
        TransactionRequestDTO request = new TransactionRequestDTO();
        request.setTransactionId("test-transaction-id");

        when(externalApiService.getClient(request, token)).thenReturn(Mono.error(() -> new RuntimeException("test error") ) );

       // when
        getWebTestClient().post().uri("/api/client")
                .header(CommonConstants.AUTHORIZATION, token)
                .bodyValue(request)
                .exchange()
        // then
                .expectStatus().is5xxServerError();
    }

    @Test
    @DisplayName("Should return transaction when /api/trancaction endpoint is requested with valid token and body")
    void getTransaction_shouldReturnTransactionResponseDTO() {

        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setTransactionId("12345");


        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        responseDTO.setFx(new TransactionResponseDTO.Fx());
        responseDTO.setTransaction(new TransactionResponseDTO.Transaction());
        responseDTO.setCustomerInfo(new TransactionResponseDTO.CustomerInfo());
        responseDTO.setMerchant(new TransactionResponseDTO.Merchant());

        when(externalApiService.getTransactionDetail(eq(requestDTO), anyString()))
                .thenReturn(Mono.just(responseDTO));

        getWebTestClient().post()
                .uri("/api/transaction")
                .header(CommonConstants.AUTHORIZATION, "Bearer some-token")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TransactionResponseDTO.class)
                .isEqualTo(responseDTO);
    }

    @Test
    @DisplayName("Should return internal server error when /api/trancaction endpoint is requested but external endpoint returns not expected result")
    public void getTransactionDetail_ReturnsInternalServerError_WhenExternalApiServiceReturnsError() {
        // Given
        String token = "test-token";
        TransactionRequestDTO request = new TransactionRequestDTO();
        request.setTransactionId("test-transaction-id");

        when(externalApiService.getTransactionDetail(request, token)).thenReturn(Mono.error(new RuntimeException("test error")));

        //When
        getWebTestClient().post().uri("/api/transaction")
                .header(CommonConstants.AUTHORIZATION, token)
                .bodyValue(request)
        //then
                .exchange()
                .expectStatus().is5xxServerError();
    }
    @Test
    @DisplayName("Should return transaction list  when /api/trancaction/list endpoint is requested with valid token and body")
    void testGetTransactionsList() {
        // Given
        // Create a mock response
        // Mock the external API service to return the dummy response
        TransactionListRequestDTO request = TransactionListRequestDTO.builder()
                .fromDate(LocalDate.now().minusDays(7))
                .toDate(LocalDate.now())
                .build();


        TransactionListResponseDTO response = new TransactionListResponseDTO();
        response.setPer_page(10);
        response.setCurrent_page(1);
        response.setNext_page_url(null);
        response.setPrev_page_url(null);
        response.setFrom(1);
        response.setTo(10);
        response.setData(Collections.emptyList());


        when(externalApiService.getTransactionsList(request, "token"))
                .thenReturn(Mono.just(response));

        //when
        // Send a request to the controller method using the WebTestClient
        getWebTestClient().post()
                .uri("/api/transactions/list")
                .header(CommonConstants.AUTHORIZATION, "token")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
        // Then Assert the response status and body
                .expectStatus().isOk()
                .expectBody(TransactionListResponseDTO.class)
                .isEqualTo(response);
    }

}
