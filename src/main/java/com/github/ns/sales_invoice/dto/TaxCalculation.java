package com.github.ns.sales_invoice.dto;

import java.math.BigDecimal;

public record TaxCalculation(
    BigDecimal icmsBase,
    BigDecimal icmsValue,
    BigDecimal icmsSubstitutionBase,
    BigDecimal icmsSubstitutionValue
) {}