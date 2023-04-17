package com.financialhouse.reporting;

import com.financialhouse.reporting.dto.TransactionReportRequestDTO;
import com.financialhouse.reporting.dto.TransactionReportResponseDTO;
import com.financialhouse.reporting.externalApi.ExternalApiService;
import com.financialhouse.reporting.util.CommonConstants;
import com.financialhouse.reporting.util.TransactionsReportResponseMockData;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "PT36S")
public class ReportApiController {

    @LocalServerPort
    private int port;
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ExternalApiService externalApiService;

    @Test
    public void testGetTransactionsReport() {

        // Prepare mock data
        TransactionReportResponseDTO response = TransactionsReportResponseMockData.createMockData();

        TransactionReportRequestDTO request = TransactionReportRequestDTO.builder()
                .fromDate(LocalDate.of(2015, 7, 1))
                .toDate(LocalDate.of(2015, 10, 1))
                .merchant(1)
                .acquirer(1)
                .build();

        when(externalApiService.getTransactionsReport(Mockito.any(TransactionReportRequestDTO.class), Mockito.anyString()))
                .thenReturn(Mono.just(response));

        String token = "test-token";

        ClientHttpConnector connector = new ReactorClientHttpConnector(
                HttpClient.create().secure(t -> t.sslContext(getSslContext()))); // create connector with custom SSL context

        webTestClient = WebTestClient
                .bindToServer(connector)
                .baseUrl("https://localhost:" + port)
                .build();


        // Call API and verify response
        webTestClient.post()
                .uri("/api/transactions/report")
                .header(CommonConstants.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody(TransactionReportResponseDTO.class)
                .isEqualTo(response);
    }

    private SslContext getSslContext() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new FileInputStream("C:\\Users\\OlcayYalgin\\keystore.jks"), "Oznur_6237".toCharArray());
            trustManagerFactory.init(keystore);

            KeyManagerFactory kmf =
                    KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keystore, "Oznur_6237".toCharArray());

            return SslContextBuilder.forClient()
                    .keyManager(kmf)
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error creating SSL context", e);
        }
    }
}
