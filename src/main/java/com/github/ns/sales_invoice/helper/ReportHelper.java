package com.github.ns.sales_invoice.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.github.ns.sales_invoice.dto.enums.MediaTypeDTO;

import jakarta.servlet.ServletContext;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public abstract class ReportHelper {

    @Autowired
    protected ServletContext context;

    private static final String BASE_PATH = "/report/";

    @Getter
    @Setter
    private static String reportName = "";

    public static JasperPrint fillReport(
            Map<String, Object> parameters,
            Collection<?> data) throws JRException {

        setReportName(reportName);

        String reportPath = BASE_PATH + getReportName();
        InputStream jaspInputStream = ReportHelper.class.getResourceAsStream(reportPath);

        if (jaspInputStream == null) {
            throw new IllegalArgumentException("File not found in classpath at: " + reportPath);
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(jaspInputStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

        return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    }

    public static byte[] exportPdf(String reportName,
            Map<String, Object> parameters,
            Collection<?> data) throws JRException {
        setReportName(reportName);

        JasperPrint print = fillReport(parameters, data);
        return JasperExportManager.exportReportToPdf(print);
    }

    public static byte[] exportExcel(String reportName,
            Map<String, Object> parameters,
            Collection<?> data) throws JRException {
        setReportName(reportName);

        JasperPrint print = fillReport(parameters, data);

        JRXlsxExporter exporter = new JRXlsxExporter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));

        SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
        config.setOnePagePerSheet(false);
        config.setDetectCellType(true);
        config.setCollapseRowSpan(false);

        exporter.setConfiguration(config);
        exporter.exportReport();

        return baos.toByteArray();
    }

    public static byte[] exportCsv(String reportName,
            Map<String, Object> parameters,
            Collection<?> data) throws JRException {
        setReportName(reportName);

        JasperPrint print = fillReport(parameters, data);

        JRCsvExporter exporter = new JRCsvExporter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));

        SimpleCsvExporterConfiguration config = new SimpleCsvExporterConfiguration();
        config.setFieldDelimiter(";");
        config.setRecordDelimiter("\n");
        exporter.setConfiguration(config);

        exporter.exportReport();

        return baos.toByteArray();
    }

    public static byte[] generateReport(Map<String, Object> line, String jrxmlName, Integer type) throws JRException, IOException {
        try {
            List<Map<String, ?>> col = new ArrayList<>();
            MediaTypeDTO mediaTypeDTO = MediaTypeDTO.fromCode(type);
            col.add(line);
            JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(col);
            return switch (mediaTypeDTO) {
                case PDF -> exportPdf(data, jrxmlName);
                // Todo: implement anothers parses
                // case XLSX -> exportExcel(jrxmlName, parameters, data);
                // case CSV -> exportCsv(jrxmlName, parameters, data);
                default -> throw new IllegalArgumentException("Unsupported media type: " + mediaTypeDTO);
            };
        } catch (JRException e) {
            throw new JRException("Error generating report: " + e.getLocalizedMessage(), e);
        }
    }

    public static byte[] exportPdf(JRDataSource dataSource, String reportName) throws JRException, IOException {
        setReportName(reportName);

        JasperReport jasperReport;
        jasperReport = getCompiledReport(getReportName());

        jasperReport.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");

        return JasperRunManager.runReportToPdf(jasperReport, null, dataSource);
    }

    private static JasperReport getCompiledReport(String jrxml) throws JRException, IOException {
        ClassPathResource jrxmlRes = new ClassPathResource(BASE_PATH + jrxml);
        File tempJasper = File.createTempFile("report-", ".jasper");

        JasperCompileManager.compileReportToFile(jrxmlRes.getFile().getAbsolutePath(), tempJasper.getAbsolutePath());
        return (JasperReport) JRLoader.loadObject(tempJasper);
    }
}
