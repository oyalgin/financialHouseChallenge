package com.financialhouse.reporting.externalApi;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;

public interface ExternalApiService {

    LoginResponseDTO userLogin(LoginRequestDTO request);
}
