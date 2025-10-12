package com.github.ns.sales_invoice.dto;

import java.math.BigDecimal;

public record Product(
    String productCode,
    String productDescription,
    String fiscalClassification,
    String taxSituation,
    String unit,
    BigDecimal quantity,
    BigDecimal unitPrice,
    BigDecimal totalPrice,
    BigDecimal icmsRate,
    BigDecimal ipiValue
) {}