package com.github.ns.sales_invoice.dto;

public record Carrier(
    String name,
    String freightResponsibility, // ex: "1 ISSUER / 2 RECIPIENT"
    String vehiclePlate,
    String plateState,
    String cnpjCpf,
    String address,
    String city,
    String stateRegistration
) {}