package com.github.ns.sales_invoice.dto;

import java.time.LocalDate;
import java.util.List;

public record ConsumerInvoiceNFEDTO(
        String companyName,
        String address,
        String stateRegistrationNumber,
        String cnpj,
        String series,
        String invoiceNumber,
        LocalDate issueDate,
        List<ItemsNFE> items,
        double totalAmount) {
}
