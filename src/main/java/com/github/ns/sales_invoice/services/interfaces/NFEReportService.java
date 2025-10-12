package com.github.ns.sales_invoice.services.interfaces;

import java.io.IOException;
import java.util.List;

import com.github.ns.sales_invoice.dto.ReportModel;

import net.sf.jasperreports.engine.JRException;

public interface NFEReportService {
    public byte[] generateReport(List<?> data, String jrxmlName, Integer type) throws JRException;
    public byte[] generateReportModel2(ReportModel invoice, Integer type ) throws JRException, IOException;
    // public byte[] generateReport(Map<String, Object> parameters, String jrxmlName, Integer type) throws JRException;
}
