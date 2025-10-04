package com.github.ns.sales_invoice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ReportModel(
        String invoiceNumber,
        String copy,
        String format,
        Party issuer,
        Party sender,
        Party recipient,
        String operationNature,
        String cfop,
        String substituteStateRegistration,
        String stateRegistration,
        LocalDate issuanceDeadline,
        LocalDate issueDate,
        LocalDate exitEntryDate,
        LocalTime exitTime,
        String invoiceReference,
        List<Product> products,
        TaxCalculation taxCalculation,
        BigDecimal totalProductsValue,
        BigDecimal freightValue,
        BigDecimal insuranceValue,
        BigDecimal otherAdditionalExpenses,
        BigDecimal totalIpiValue,
        BigDecimal totalInvoiceValue,
        Carrier carrier,
        List<Package> transportedPackages,
        String additionalInformation,
        String reservedForTaxAuthority,
        String controlFormNumber,
        AidfPrinter aidfPrinterData,
        String receivedFromIssuerName,
        LocalDate receiptDate,
        String receiverSignature) {
}