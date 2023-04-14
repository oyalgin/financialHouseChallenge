package com.financialhouse.reporting.controller;

import com.financialhouse.reporting.util.CommonConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCommonController {
        @GetMapping("/")
        @ResponseStatus(HttpStatus.OK)
        public String runApplication() {
            return CommonConstants.APPLICATION_RUNNING;
        }
}

