package com.financialhouse.reporting.externalApi;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class rdpPymntService implements ExternalApiService{

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rdpPymnt.baseUrl}")
    private String baseUrl;

    @Value("${rdpPymnt.serviceEndpoints.login}")
    private String loginEndpoint;

    @Override
    public LoginResponseDTO userLogin(LoginRequestDTO request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
       // HttpEntity<LoginRequestDTO> entity = new HttpEntity<>(request,headers);

        HttpEntity<LoginRequestDTO> entity = new HttpEntity<>(request);

        ResponseEntity<LoginResponseDTO> response = restTemplate.exchange(baseUrl + loginEndpoint, HttpMethod.POST, entity, LoginResponseDTO.class);

        return response.getBody();
    }
}
