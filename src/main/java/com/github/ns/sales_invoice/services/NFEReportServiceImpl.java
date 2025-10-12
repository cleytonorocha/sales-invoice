package com.github.ns.sales_invoice.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.ns.sales_invoice.dto.ReportModel;
import com.github.ns.sales_invoice.helper.ReportHelper;
import com.github.ns.sales_invoice.services.interfaces.NFEReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class NFEReportServiceImpl implements NFEReportService {

    @Override
    public byte[] generateReport(List<?> data, String jrxmlName, Integer type) throws JRException {
        return null;
        // try {
        // Map<String, Object> parameters = new HashMap<>();
        // ClassPathResource imgFile = new ClassPathResource("static/images/logo.svg");
        // parameters.put("logo", imgFile.getInputStream());

        // MediaTypeDTO mediaTypeDTO = MediaTypeDTO.fromCode(type);

        // return switch (mediaTypeDTO) {
        // case PDF -> exportPdf(jrxmlName, parameters, data);
        // case XLSX -> exportExcel(jrxmlName, parameters, data);
        // case CSV -> exportCsv(jrxmlName, parameters, data);
        // default -> throw new IllegalArgumentException("Unsupported media type: " +
        // mediaTypeDTO);
        // };
        // } catch (IOException | JRException e) {
        // throw new JRException("Error generating report: " + e.getLocalizedMessage(),
        // e);
        // }
    }

    @Override
    public byte[] generateReportModel2(ReportModel invoice, Integer type) throws JRException, IOException {

        // Cria o mapa do invoice
        Map<String, Object> invoiceMap = new HashMap<>();

        // Header
        invoiceMap.put("invoiceNumber", invoice.invoiceNumber());
        invoiceMap.put("operationType", invoice.operationType());
        invoiceMap.put("copyType", invoice.copyType());

        // Issuer
        invoiceMap.put("issuerName", invoice.issuerName());
        invoiceMap.put("issuerAddress", invoice.issuerAddress());
        invoiceMap.put("issuerDistrict", invoice.issuerDistrict());
        invoiceMap.put("issuerCity", invoice.issuerCity());
        invoiceMap.put("issuerState", invoice.issuerState());
        invoiceMap.put("issuerZipCode", invoice.issuerZipCode());
        invoiceMap.put("issuerPhoneFax", invoice.issuerPhoneFax());

        // Recipient / Sender
        invoiceMap.put("recipientName", invoice.recipientName());
        invoiceMap.put("recipientCnpj", invoice.recipientCnpj());
        invoiceMap.put("recipientCpf", invoice.recipientCpf());
        invoiceMap.put("recipientAddress", invoice.recipientAddress());
        invoiceMap.put("recipientDistrict", invoice.recipientDistrict());
        invoiceMap.put("recipientCity", invoice.recipientCity());
        invoiceMap.put("recipientState", invoice.recipientState());
        invoiceMap.put("recipientZipCode", invoice.recipientZipCode());
        invoiceMap.put("recipientPhoneFax", invoice.recipientPhoneFax());
        invoiceMap.put("recipientStateRegistration", invoice.recipientStateRegistration());
        invoiceMap.put("recipientTaxSubstituteRegistration", invoice.recipientTaxSubstituteRegistration());
        invoiceMap.put("recipientCfop", invoice.recipientCfop());

        // Dates
        invoiceMap.put("issueDate", invoice.issueDate());
        invoiceMap.put("shippingDate", invoice.shippingDate());

        // Invoice
        invoiceMap.put("invoiceNumberReference", invoice.invoiceNumberReference());

        // Products (subreport)
        invoiceMap.put("products", invoice.products());

        // Tax calculation
        invoiceMap.put("icmsBase", invoice.icmsBase());
        invoiceMap.put("icmsValue", invoice.icmsValue());
        invoiceMap.put("icmsSubstitutionBase", invoice.icmsSubstitutionBase());
        invoiceMap.put("icmsSubstitutionValue", invoice.icmsSubstitutionValue());
        invoiceMap.put("totalProductsValue", invoice.totalProductsValue());
        invoiceMap.put("freightValue", invoice.freightValue());
        invoiceMap.put("insuranceValue", invoice.insuranceValue());
        invoiceMap.put("otherExpenses", invoice.otherExpenses());
        invoiceMap.put("totalIpiValue", invoice.totalIpiValue());
        invoiceMap.put("totalInvoiceValue", invoice.totalInvoiceValue());

        // Transporter
        invoiceMap.put("transporterName", invoice.transporterName());
        invoiceMap.put("transporterAddress", invoice.transporterAddress());
        invoiceMap.put("transporterCity", invoice.transporterCity());
        invoiceMap.put("transporterState", invoice.transporterState());
        invoiceMap.put("transporterCnpj", invoice.transporterCnpj());
        invoiceMap.put("transporterVehiclePlate", invoice.transporterVehiclePlate());
        invoiceMap.put("transporterVehicleState", invoice.transporterVehicleState());
        invoiceMap.put("transporterStateRegistration", invoice.transporterStateRegistration());
        invoiceMap.put("freightByRecipient", invoice.freightByRecipient());

        // Additional info
        invoiceMap.put("additionalInformation", invoice.additionalInformation());
        invoiceMap.put("reservedForTaxAuthority", invoice.reservedForTaxAuthority());

        // Control
        invoiceMap.put("formControlNumber", invoice.formControlNumber());

        // Receipt
        invoiceMap.put("receiptDate", invoice.receiptDate());
        invoiceMap.put("receiptSignature", invoice.receiptSignature());

        // Footer
        invoiceMap.put("footerMessage", invoice.footerMessage());

        // byte[] report = ReportHelper.generateReport(invoiceMap, "teste.jrxml", type);
        byte[] report = ReportHelper.generateReport(invoiceMap, "consumer_invoice_2.jrxml", type);
        return report;
    }

}
