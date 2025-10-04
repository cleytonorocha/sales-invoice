package com.github.ns.sales_invoice.services;

import com.github.ns.sales_invoice.dto.enums.MediaTypeDTO;
import com.github.ns.sales_invoice.services.interfaces.NFEReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.ns.sales_invoice.helper.ReportHelper.*;

@Service
public class NFEReportServiceImpl implements NFEReportService {

    @Override
    public byte[] generateReport(List<?> data, String jrxmlName, Integer type) throws JRException {
        try {
            Map<String, Object> parameters = new HashMap<>();
            ClassPathResource imgFile = new ClassPathResource("static/images/logo.svg");
            parameters.put("logo", imgFile.getInputStream());

            MediaTypeDTO mediaTypeDTO = MediaTypeDTO.fromCode(type);

            return switch (mediaTypeDTO) {
                case PDF -> exportPdf(jrxmlName, parameters, data);
                case XLSX -> exportExcel(jrxmlName, parameters, data);
                case CSV -> exportCsv(jrxmlName, parameters, data);
                default -> throw new IllegalArgumentException("Unsupported media type: " + mediaTypeDTO);
            };
        } catch (IOException | JRException e) {
            throw new JRException("Error generating report: " + e.getLocalizedMessage(), e);
        }
    }
}
