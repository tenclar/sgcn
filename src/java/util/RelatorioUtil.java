/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.collections.map.HashedMap;

/**
 *
 * @author admin
 */
public class RelatorioUtil {
    
     public void criaRelatorio(List listas, String caminhorelatorio, String nomerelatorio,Map parameters ) throws IOException, JRException {

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath(caminhorelatorio);
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listas, false);
        

        response.setHeader("Content-Disposition", "inline; filename=" + nomerelatorio);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        byte x1[] = JasperExportManager.exportReportToPdf(jasperPrint);
        response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();


    }
     public void criaRelatorio(List listas, String caminhorelatorio, String nomerelatorio ) throws IOException, JRException {

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath(caminhorelatorio);
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listas);
        
        Map parameters = new HashMap();
        response.setHeader("Content-Disposition", "inline; filename=" + nomerelatorio);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        byte x1[] = JasperExportManager.exportReportToPdf(jasperPrint);
        response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();


    }
     public void criaRelatoriodb(Map parameters, String caminhorelatorio ) throws IOException, JRException {

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath(caminhorelatorio);
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        
       // JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listas);
        
        //Map parameters = new HashMap();
        response.setHeader("Content-Disposition", "inline; filename=rel_quantitativo");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");
        ServletOutputStream responseStream = response.getOutputStream();

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, DbCon.getConnection());
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        byte x1[] = JasperExportManager.exportReportToPdf(jasperPrint);
        response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();


    }
     
      public void criaRelatoriodbNome(Map parameters, String caminhorelatorio,String arquivo ) throws IOException, JRException {
        Connection connection = DbCon.getConnection();
        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath(caminhorelatorio);
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        
       // JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listas);
        
        //Map parameters = new HashMap();
        response.setHeader("Content-Disposition", "inline; filename=rel_"+arquivo+".pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");
        ServletOutputStream responseStream = response.getOutputStream();

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, connection);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        byte x1[] = JasperExportManager.exportReportToPdf(jasperPrint);
        response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();


    }
    
    
}
