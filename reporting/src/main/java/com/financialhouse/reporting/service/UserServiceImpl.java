package com.financialhouse.reporting.service;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;
import com.financialhouse.reporting.externalApi.ExternalApiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    ExternalApiService externalApiService;
    @Override
    public Mono<LoginResponseDTO> login(LoginRequestDTO request) {
        //Repo check
        return externalApiService.userLogin(request);
    }
}
