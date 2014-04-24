/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDAO;
import entity.UsrPermissao;
import entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import util.FacesUtils;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    private FacesUtils facesutis = new FacesUtils();
    private Usuario usuario = null;    
    private String paswd;
    private UsrPermissao permissao;
    private DataModel<Usuario> dmLista = null;
    private List<Usuario> listaUsers = null;
    private String busca;

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    

   
    @SuppressWarnings("unchecked")
    public DataModel<Usuario> getListaUsuarios() {       
        dmLista = null; 
         if (listaUsers != null) {
          dmLista = new ListDataModel(listaUsers);        
         }
        return dmLista;
    }
    public void localiza(){
        if ( "".equals(this.busca)){
            this.busca = "%";
        }
         UsuarioDAO usuarioDAO = new UsuarioDAO();
          listaUsers = usuarioDAO.getUsuarios(this.busca);
        
    }
    public String getPaswd() {
        return paswd;
    }

    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }

    public UsrPermissao getPermissao() {
        return permissao;
    }

    public void setPermissao(UsrPermissao permissao) {
        this.permissao = permissao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void newUsuario(ActionEvent actionEvent) {
        
        this.usuario = new Usuario();
        this.permissao = new UsrPermissao();

    }

    public void saveUsuario(ActionEvent actionEvent) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.saveUsuario(this.usuario);
            facesutis.info("Cadastro Efetuado");
            this.usuario = new Usuario();
            this.listaUsers = null;
            
        } catch (Exception e) {
            facesutis.erro("Cadastro Não Efetuado! " + e.getMessage());
        }

    }
     public void updateUsuario() {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.updateUsuario(this.usuario);
            this.usuario = null;
            this.listaUsers = null;
           facesutis.info("Cadastro Efetuado!");
        } catch (Exception e) {
            facesutis.erro("Cadastro Não Efetuado! " + e.getMessage());
        }

    }
    public void editUsuario(ActionEvent actionEvent) {
        UsuarioDAO dao = new UsuarioDAO(); 
        this.permissao = new UsrPermissao();               
        Usuario u = (Usuario) (this.dmLista.getRowData());       
        this.usuario = dao.getEntity(u.getId());
    }

    public void cancelUsuario() {
        this.usuario = new Usuario();
        this.listaUsers = new ArrayList<Usuario>();
    }

    public void addPermissao() {
        
        this.permissao.setUsuario(usuario);
        this.usuario.getPermissaos().add(permissao);
        this.permissao = new UsrPermissao();
    }

    @SuppressWarnings("unchecked")
    public void imprimeRelatorioWeb() throws IOException, JRException {

        List<Usuario> listausr = new ArrayList<Usuario>();
        this.usuario = (Usuario) (this.dmLista.getRowData());
        listausr.add(usuario);
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
            String relJasper = scontext.getRealPath("/admin/usuario/relatorio/relusuarios.jasper");
            //InputStream inputStream = getClass().getResourceAsStream(relJasper);
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            ServletOutputStream responseStream = response.getOutputStream();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
            Map parameters = new HashMap();

            response.setHeader("Content-Disposition", "inline; filename=relusuarios.pdf");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("application/pdf");

            JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
            JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
            JasperExportManager.exportReportToPdf(jasperPrint);
            //response.getOutputStream().write(x1);
            responseStream.flush();
            responseStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void imprimeRelatorioWebTodos() throws IOException, JRException {
        List<Usuario> listausr = new ArrayList<Usuario>();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        listausr.addAll(usuarioDAO.getUsuarios());
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
            String relJasper = scontext.getRealPath("/admin/usuario/relatorio/relusuarios.jasper");
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            ServletOutputStream responseStream = response.getOutputStream();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
            Map parameters = new HashMap();
            response.setHeader("Content-Disposition", "inline; filename=impressao.pdf");
            response.setContentType("application/pdf");
            response.setHeader("Cache-Control", "no-cache");
            JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
            JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
            responseStream.flush();
            responseStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
