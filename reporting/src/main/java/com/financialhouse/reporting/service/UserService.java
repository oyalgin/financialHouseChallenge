package com.financialhouse.reporting.service;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<LoginResponseDTO> login(LoginRequestDTO request);
}
