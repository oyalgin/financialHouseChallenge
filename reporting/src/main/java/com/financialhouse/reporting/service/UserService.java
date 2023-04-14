package com.financialhouse.reporting.service;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;

public interface UserService {

    LoginResponseDTO login(LoginRequestDTO request);
}
