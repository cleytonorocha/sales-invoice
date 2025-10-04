package com.github.ns.sales_invoice.dto;

public record AidfPrinter(
    String aidfData,
    String printerData,
    String number
) {}