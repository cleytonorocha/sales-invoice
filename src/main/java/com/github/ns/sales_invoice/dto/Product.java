package com.github.ns.sales_invoice.dto;

import java.math.BigDecimal;

public record Product(
    String code,
    String description,
    String classification,
    String status,
    String unit,
    BigDecimal quantity,
    BigDecimal unitValue,
    BigDecimal totalValue,
    String taxRates,
    BigDecimal ipiValue,
    String fiscalProductInfo,
    String icms,
    String ipi
) {}