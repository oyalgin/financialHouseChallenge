package com.financialhouse.reporting.util;

import com.financialhouse.reporting.dto.TransactionReportResponseDTO;

import java.util.Arrays;
import java.util.List;

public class TransactionsReportResponseMockData {

    public static TransactionReportResponseDTO createMockData() {
        TransactionReportResponseDTO response = new TransactionReportResponseDTO();
        response.setStatus("APPROVED");
        response.setResponse(createTransactionReportData());
        return response;
    }

    private static List<TransactionReportResponseDTO.TransactionReportData> createTransactionReportData() {
        TransactionReportResponseDTO.TransactionReportData data1 = new TransactionReportResponseDTO.TransactionReportData();
        data1.setCount(283);
        data1.setTotal(28300);
        data1.setCurrency("USD");

        TransactionReportResponseDTO.TransactionReportData data2 = new TransactionReportResponseDTO.TransactionReportData();
        data2.setCount(280);
        data2.setTotal(1636515);
        data2.setCurrency("EUR");

        return Arrays.asList(data1, data2);
    }
}