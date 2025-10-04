package com.github.ns.sales_invoice.dto;

import java.math.BigDecimal;

public record Package(
    Integer quantity,
    String type,
    String brand,
    String number,
    BigDecimal grossWeight,
    BigDecimal netWeight
) {}