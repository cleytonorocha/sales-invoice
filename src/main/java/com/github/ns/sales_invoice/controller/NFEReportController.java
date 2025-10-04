package com.github.ns.sales_invoice.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ns.sales_invoice.dto.ConsumerInvoiceNFEDTO;
import com.github.ns.sales_invoice.dto.ReportModel;
import com.github.ns.sales_invoice.helper.interfaces.MediaTypes;
import com.github.ns.sales_invoice.services.interfaces.NFEReportService;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;


@RestController
@AllArgsConstructor
@RequestMapping(value = "reports", produces = {
        MediaTypes.APPLICATION_CSV_VALUE,
        MediaTypes.APPLICATION_PDF_VALUE,
        MediaTypes.APPLICATION_XLSX_VALUE})

public class NFEReportController {

    private NFEReportService reportService;

    @PostMapping("/consumerInvoiceReport{type}")
    public ResponseEntity<byte[]> generateConsumerInvoiceReport(@PathVariable("type") Integer type, @RequestBody ConsumerInvoiceNFEDTO invoice) {
        try {
            List<ConsumerInvoiceNFEDTO> data = List.of(invoice);
            byte[] file = reportService.generateReport(data, "consumer_invoice.jrxml", type);

            String fileName = switch (type) {
                case 1 -> "report.pdf";
                case 2 -> "report.xlsx";
                case 3 -> "report.csv";
                default -> "report";
            };
            String contentType = switch (type) {
                case 1 -> MediaTypes.APPLICATION_PDF_VALUE;
                case 2 -> MediaTypes.APPLICATION_XLSX_VALUE;
                case 3 -> MediaTypes.APPLICATION_CSV_VALUE;
                default -> throw new IllegalStateException("Unexpected value: " + type);
            };
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(file);

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("consumerInvoiceReport2/{type}")
    public ResponseEntity<byte[]> generateConsumerInvoiceReport2(@PathVariable("type") Integer type,
            @RequestBody ReportModel invoice) {
        try {

            List<ReportModel> data = List.of(invoice);
            byte[] file = reportService.generateReport(data, "consumer_invoice._2.jrxml", type);

            String fileName = switch (type) {
                case 1 -> "report.pdf";
                case 2 -> "report.xlsx";
                case 3 -> "report.csv";
                default -> "report";
            };
            String contentType = switch (type) {
                case 1 -> MediaTypes.APPLICATION_PDF_VALUE;
                case 2 -> MediaTypes.APPLICATION_XLSX_VALUE;
                case 3 -> MediaTypes.APPLICATION_CSV_VALUE;
                default -> throw new IllegalStateException("Unexpected value: " + type);
            };
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(file);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
