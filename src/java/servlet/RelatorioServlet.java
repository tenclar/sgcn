package servlet;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import util.DbCon;

/**
 * Servlet implementation class for Servlet: RelatorioServlet
 *
 */
@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet"})
 public class RelatorioServlet extends HttpServlet {
   static final long serialVersionUID = 1L;
   
   @SuppressWarnings({ "unchecked", "deprecation" })
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
	   String path = getServletContext().getRealPath("/relatorios/jasper/");
	   Map parameters = new HashMap();
	   String relJasper = "";
	   String BRASAO = new String();
	   
	   switch (Integer.parseInt(request.getParameter("nrelat"))) {
	   case 1:
		   relJasper = path + "/relatorioClasseDestino.jasper";
		   break;
	   case 2:
		   relJasper = path + "/relatorioClasseFuncao.jasper";
		   break;
	   case 3:
		   relJasper = path + "/relatorioDiarias.jasper";
		   break;
	   case 4:
		   relJasper = path + "/RelatorioViagensDetalhado.jasper";
		   String[] aux = new String[3];
		   aux = request.getParameter("dataInicial").split("/");
           Date dataInicial = new Date( aux[2]+"/"+aux[1]+"/"+aux[0] );
		   parameters.put("dataInicial", dataInicial);
		   aux = request.getParameter("dataFinal").split("/");
           Date dataFinal = new Date( aux[2]+"/"+aux[1]+"/"+aux[0] );
		   parameters.put("dataFinal", dataFinal);
		   break;
	   case 5:
		   relJasper = path + "/RelatorioViagemDetalhado.jasper";
		   parameters.put("idViagem", Integer.parseInt(request.getParameter("idViagem")));
		   break;
	   case 6:
		   relJasper = path + "/RelatorioPropostaDiarias.jasper";
		   parameters.put("idViagem", Integer.parseInt(request.getParameter("idViagem")));
		   break;
	   case 7:
		   relJasper = path + "/RelatorioViagem.jasper";
		   parameters.put("idViagem", Integer.parseInt(request.getParameter("idViagem")));
		   parameters.put("idViajante", Integer.parseInt(request.getParameter("idViajante")));
		   break;
	

	   //case x:
		   //relJasper = path + "/relatorioClasseDestino.jasper";
		   //parameters.put("PARAM1", Integer.parseInt(request.getParameter("codrelat")));
		   //parameters.put("PARAM2", request.getParameter("nome"));
		   //break;
	   }


	   try{
           BRASAO = getServletContext().getRealPath("/imgs/logo_brasao.gif");
           @SuppressWarnings("unused")
		FileReader reader = new FileReader( BRASAO );
           reader = null;
       }catch ( IOException e ){
           request.setAttribute("msg","Falha ao carregar as imagens.");
       }
       parameters.put("brasao", BRASAO);
       
	   
	   JasperPrint report = null;
	   
	   try {
		   report = JasperFillManager.fillReport(relJasper, parameters, DbCon.getConnection());
		   response.setContentType("application/pdf");
		   
		   byte x1[] = JasperExportManager.exportReportToPdf(report);
		   
		   response.getOutputStream().write(x1);
		   
	   }
	   catch (JRException e) {
		System.out.println(e.getMessage());
	}
	   

   }


   protected void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
       processRequest(request, response);
   }
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
       processRequest(request, response);
   }
   public String getServletInfo() {
       return "Short description";
   }
	
 	  	    
}