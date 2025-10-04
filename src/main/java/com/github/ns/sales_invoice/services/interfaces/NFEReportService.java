package com.github.ns.sales_invoice.services.interfaces;

import net.sf.jasperreports.engine.JRException;

import java.util.List;

public interface NFEReportService {
    public byte[] generateReport(List<?> data, String jrxmlName, Integer type) throws JRException;
}
