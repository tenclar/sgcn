package beans.mgc;

import dao.mgc.CursoDAO;
import entity.mgc.Curso;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import util.FacesUtils;
import util.RelatorioUtil;

@ManagedBean
@ViewScoped
public class CursoBean implements Serializable {

    
    private FacesUtils facesutils;
    private DataModel dmListaCursos;
    private Curso curso;
    private List<Curso> listaCursos = null;
    private String argumento;
    private FacesContext context;

    
    public String getArgumento() {
        return argumento;
    }

    public void setArgumento(String argumento) {
        this.argumento = argumento;
    }
    
    
    
    public void CursoBean() {
    }
    
    @SuppressWarnings("unchecked")
    public DataModel getListaCursos() {
        
        if (listaCursos == null) {
            CursoDAO cursoDAO = new CursoDAO();
            listaCursos = cursoDAO.getCursos();
        }
        dmListaCursos = new ListDataModel(listaCursos);
        return dmListaCursos;
    }
    
    public void localizaCurso(){
        CursoDAO cursoDAO = new CursoDAO();
        listaCursos = cursoDAO.getCurso(this.argumento);
        
    }
    
    public List<SelectItem> getSelectItemsCurso() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        CursoDAO cursoDAO = new CursoDAO();
        for (Curso c : cursoDAO.getCursos()) {
            toReturn.add(new SelectItem(c, c.getNome()));
        }
        return toReturn;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void newCurso() {
        this.curso = new Curso();
    }
    public void saveCurso() {
        
        CursoDAO cursoDAO = new CursoDAO();
        try {
            if (listaCursos == null) {
                listaCursos = cursoDAO.getCursos();
            }
            if (this.listaCursos.contains(this.curso)) {
                throw new Exception(" Já Existe ");
            } else {
                cursoDAO.saveCurso(this.curso);
                this.listaCursos = null;                
                facesutils.aviso("Cadastro Efetuado !");
            }
        } catch (Exception e) {
            
            facesutils.erro("Cadastro não Efetuado !"+e.getMessage());
        }
    }

    public void editCurso(ActionEvent actionEvent) {
        this.curso = (Curso) (this.dmListaCursos.getRowData());
    }

    public void updateCurso() {
        
        CursoDAO cursoDAO = new CursoDAO();
        try {
            cursoDAO.updateCurso(curso);
            this.listaCursos = null;
            
            facesutils.aviso("Cadastro Efetuado !");
        } catch (Exception e) {
            
            facesutils.erro("Cadastro não Efetuado !"+e.getMessage());
        }
    }

    public void cancelCurso() {
        this.curso = null;
    }
    
    @SuppressWarnings("unchecked")
    public void imprimeRelatorioWebTodos() throws IOException, JRException {
        
         CursoDAO cursoDAO = new CursoDAO();
         List<Curso> lista = cursoDAO.getCursos();
          String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_lista_cursos");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, "lista_publico");
     }
}
