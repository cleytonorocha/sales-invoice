package com.github.ns.sales_invoice.dto;

public record Party(
    String name,
    String cnpjCpf,
    String address,
    String district,
    String city,
    String state,
    String postalCode,
    String phoneFax,
    String stateRegistration
) {}