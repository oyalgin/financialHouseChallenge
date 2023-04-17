package com.financialhouse.reporting.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionListResponseDTO {
    private int per_page;
    private int current_page;
    private String next_page_url;
    private String prev_page_url;
    private int from;
    private int to;
    private List<TransactionResponseDTO> data;

    @Data
    public static class TransactionResponseDTO {
        private FxResponseDTO fx;
        private CustomerInfoResponseDTO customerInfo;
        private MerchantResponseDTO merchant;
        private IpnResponseDTO ipn;
        private TransactionInfoResponseDTO transaction;
        private AcquirerResponseDTO acquirer;
        private boolean refundable;
    }

    @Data
    public static class FxResponseDTO {
        private MerchantResponseDTO merchant;
    }

    @Data
    public static class CustomerInfoResponseDTO {
        private String number;
        private String email;
        private String billingFirstName;
        private String billingLastName;
    }

    @Data
    public static class MerchantResponseDTO {
        private String referenceNo;
        private String status;
        private String operation;
        private String message;
        private String created_at;
        private String transactionId;
    }

    @Data
    public static class IpnResponseDTO {
        private boolean received;
    }

    @Data
    public static class TransactionInfoResponseDTO {
        private MerchantResponseDTO merchant;
    }

    @Data
    public static class AcquirerResponseDTO {
        private int id;
        private String name;
        private String code;
        private String type;
    }
}
