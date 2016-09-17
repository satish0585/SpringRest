package com.cg.esol.ivu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportUtility {
	
	public void generateReport(List<Object> reportRecordList) throws JRException, IOException {

		InputStream inputStream = this.getClass().getResourceAsStream("/report.jrxml");
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(reportRecordList);
		
		Map<Object,Object> parameters = new HashMap<Object,Object>();
        
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);   
        FacesContext context = FacesContext.getCurrentInstance();   
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();   
        
        response.addHeader("Content-disposition","attachment;filename=reporte.pdf");   
        response.setContentLength(bytes.length);   
        response.getOutputStream().write(bytes);   
        response.setContentType("application/pdf");   
        context.responseComplete(); 
	}
	
	private static ReportUtility reportUtility = null;
	
	public static ReportUtility getInstance(){
		if(reportUtility == null) {
			return reportUtility = new ReportUtility();
		}
		return reportUtility;
	}
}
