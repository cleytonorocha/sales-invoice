package com.github.ns.sales_invoice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ReportModel(
  // Header
  String invoiceNumber,
  String operationType, // v
  String copyType,

  // Issuer
  String issuerName,
  String issuerAddress,
  String issuerDistrict,
  String issuerCity,
  String issuerState,
  String issuerZipCode,
  String issuerPhoneFax,

  // Recipient / Sender
  String recipientName,
  String recipientCnpj,
  String recipientCpf,
  String recipientAddress,
  String recipientDistrict,
  String recipientCity,
  String recipientState,
  String recipientZipCode,
  String recipientPhoneFax,
  String recipientStateRegistration,
  String recipientTaxSubstituteRegistration,
  String recipientCfop,

  // Dates
  LocalDate issueDate,
  LocalDate shippingDate,

  // Invoice
  String invoiceNumberReference,

  // Product data (now a List)
  List<Product> products,

  // Tax calculation
  BigDecimal icmsBase,
  BigDecimal icmsValue,
  BigDecimal icmsSubstitutionBase,
  BigDecimal icmsSubstitutionValue,
  BigDecimal totalProductsValue,
  BigDecimal freightValue,
  BigDecimal insuranceValue,
  BigDecimal otherExpenses,
  BigDecimal totalIpiValue,
  BigDecimal totalInvoiceValue,

  // Transporter / Volumes
  String transporterName,
  String transporterAddress,
  String transporterCity,
  String transporterState,
  String transporterCnpj,
  String transporterVehiclePlate,
  String transporterVehicleState,
  String transporterStateRegistration,
  Boolean freightByRecipient, // true = recipient pays freight

  // Additional info
  String additionalInformation,
  String reservedForTaxAuthority,

  // Control
  String formControlNumber,

  // Receipt
  LocalDate receiptDate,
  String receiptSignature,

  // Footer
  String footerMessage) {
}
