package com.financialhouse.reporting.externalApi;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;
import reactor.core.publisher.Mono;

public interface ExternalApiService {

    Mono<LoginResponseDTO> userLogin(LoginRequestDTO request);
}
