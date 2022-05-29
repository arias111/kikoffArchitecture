package com.itis.kikoff.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {
    private String PROJECT_PATH = "D:\\Java\\Projects\\kikoffArchitecture\\backend";
    private int count = 0;
    private String REPORT_pdf     = "\\src\\test\\java\\com\\itis\\kikoff\\report";
    private String REPORT_pattern = "D:\\Java\\Projects\\kikoffArchitecture\\backend\\src\\test\\java\\com\\itis\\kikoff\\report.jrxml";

    private File reportPattern;
    private Map<String, Object> parameters;
    private JRBeanCollectionDataSource beanColDataSource;
    private ArrayList<DataBean> dataBeanList;

    private JasperDesign jasperDesign;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint ;

    public void create() throws JRException
    {
        Data dataBeanMaker = new Data();
        dataBeanList = dataBeanMaker.getDataBeanList("Ошибка");
        beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
        parameters = new HashMap<>();
        parameters.put("DATE", new Date());

        reportPattern = new File(REPORT_pattern);
        jasperDesign  = JRXmlLoader.load(reportPattern);
        jasperReport  = JasperCompileManager.compileReport(jasperDesign);
        jasperPrint   = JasperFillManager.fillReport(jasperReport,
                parameters,
                beanColDataSource);
        count++;
        REPORT_pdf = REPORT_pdf + count + ".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,
                PROJECT_PATH + REPORT_pdf);
    }

    public static void main(String[] args)
    {
        System.out.println("Начало генерации отчёта");
        try {
            new ReportGenerator().create();
            System.out.println("Генерация отчёта завершена");
        } catch (Exception e) {
            System.err.println("Exception : " + e);
        }
    }
}
