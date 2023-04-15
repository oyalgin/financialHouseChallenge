package com.financialhouse.reporting.controller;

import com.financialhouse.reporting.dto.LoginRequestDTO;
import com.financialhouse.reporting.dto.LoginResponseDTO;
import com.financialhouse.reporting.service.UserService;
import com.financialhouse.reporting.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = CommonConstants.MAIN_API)
public class UserApiController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public Mono<ResponseEntity<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
       return  userService.login(loginRequestDTO)
               .map(ResponseEntity::ok)
               .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
