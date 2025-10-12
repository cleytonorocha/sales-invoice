package com.github.ns.sales_invoice.dto;

import java.awt.image.BufferedImage;
public record Party(
    BufferedImage logo,
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