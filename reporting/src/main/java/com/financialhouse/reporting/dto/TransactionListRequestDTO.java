package com.financialhouse.reporting.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionListRequestDTO {

    @JsonProperty("fromDate")
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate fromDate;

    @JsonProperty("toDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    private LocalDate toDate;

    @JsonProperty("merchant")
    private Integer merchant;

    @JsonProperty("acquirer")
    private Integer acquirer;

    @JsonProperty("status")
    private TransactionStatus status;

    @JsonProperty("operation")
    private TransactionOperation operation;

    @JsonProperty("paymentMethod")
    private PaymentMethod paymentMethod;
    private ErrorCode errorCode;
    private FilterField filterField;
    private String filterValue;
    private Integer page;

    public String getFromDate() {
        return fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getToDate() {
        return toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public enum TransactionStatus {
        APPROVED,
        WAITING,
        DECLINED,
        ERROR
    }

    public enum TransactionOperation {
        DIRECT,
        REFUND,
        _3D,
        _3DAUTH,
        STORED
    }

    public enum PaymentMethod {
        CREDITCARD,
        CUP,
        IDEAL,
        GIROPAY,
        MISTERCASH,
        STORED,
        PAYTOCARD,
        CEPBANK,
        CITADEL
    }

    public enum ErrorCode {
        DO_NOT_HONOR("Do not honor"),
        INVALID_TRANSACTION("Invalid Transaction"),
        INVALID_CARD("Invalid Card"),
        NOT_SUFFICIENT_FUNDS("Not sufficient funds"),
        INCORRECT_PIN("Incorrect PIN"),
        INVALID_COUNTRY_ASSOCIATION("Invalid country association"),
        CURRENCY_NOT_ALLOWED("Currency not allowed"),
        _3D_SECURE_TRANSPORT_ERROR("3-D Secure Transport Error"),
        TRANSACTION_NOT_PERMITTED_TO_CARDHOLDER("Transaction not permitted to cardholder");

        private final String value;

        ErrorCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum FilterField {
        TRANSACTION_UUID("Transaction UUID"),
        CUSTOMER_EMAIL("Customer Email"),
        REFERENCE_NO("Reference No"),
        CUSTOM_DATA("Custom Data"),
        CARD_PAN("Card PAN");

        private final String value;

        FilterField(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
