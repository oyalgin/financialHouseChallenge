package com.financialhouse.reporting.dto;

import lombok.Data;

@Data
public class ClientResponseDTO {
    private CustomerInfo customerInfo;

    @Data
    public static class CustomerInfo {
        private String billingFirstName;
        private String billingLastName;
        private String issueNumber;
        private String email;
        private String billingCompany;
        private String billingCity;
        private String updated_at;
        private String created_at;
        private int id;
    }
}
