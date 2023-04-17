package com.financialhouse.reporting.dto;

import lombok.Data;

import lombok.Data;

@Data
public class TransactionResponseDTO {
    private Fx fx;
    private Transaction transaction;
    private CustomerInfo customerInfo;
    private Merchant merchant;

    @Data
    public static class Fx {
        private Merchant merchant;

        @Data
        public static class Merchant {
            private int originalAmount;
            private String originalCurrency;
        }
    }

    @Data
    public static class Transaction {
        private Merchant merchant;

        @Data
        public static class Merchant {
            private String referenceNo;
            private int merchantId;
            private String status;
            private String channel;
            private String customData;
            private String chainId;
            private String type;
            private int agentInfoId;
            private String operation;
            private String updated_at;
            private String created_at;
            private int id;
            private int fxTransactionId;
            private int acquirerTransactionId;
            private String code;
            private String message;
            private String transactionId;
            private Agent agent;

            @Data
            public static class Agent {
                private int id;
                private String customerIp;
                private String customerUserAgent;
                private String merchantIp;
                private String merchantUserAgent;
                private String created_at;
                private String updated_at;
                private String deleted_at;
            }
        }
    }

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

    @Data
    public static class Merchant {
        private String name;
    }
}

