/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mac;

import dao.mac.ContatoDAO;
import dao.mac.ConvenioDAO;
import entity.Telefone;
import entity.mac.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class ConvenioBean implements Serializable {

    private FacesContext context;
    private DataModel DMlistaConvenios = null;
    private Convenio convenio;
    private List<Convenio> listaConv = null;
    private Contato contato;
    private Investimento investimento;
    private String busca;
    private ConvHistorico historico;
    private HistoricoInterno historicoInterno;
    private Vigencia vigencia;
    private Aditivo aditivo;
    private String caminho;
    private String arquivo;
    private Anexo anexo;
    private Arquivo arq;
    private StreamedContent file;
    
    private boolean inserirContra;
    private Telefone telefone;
    private boolean nvigencia;
    private boolean nadtivo;
    private ContraPartida contrapartida;
    private boolean inserirHistInt;
    private boolean inserirHist;
    private boolean inserirContato;

    public ContraPartida getContrapartida() {
        return contrapartida;
    }

    public void setContrapartida(ContraPartida contrapartida) {
        this.contrapartida = contrapartida;
    }
    
    

    public HistoricoInterno getHistoricoInterno() {
        return historicoInterno;
    }

    public void setHistoricoInterno(HistoricoInterno historicoInterno) {
        this.historicoInterno = historicoInterno;
    }

    public Arquivo getArq() {
        return arq;
    }

    public void setArq(Arquivo arq) {
        this.arq = arq;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public Aditivo getAditivo() {
        return aditivo;
    }

    public void setAditivo(Aditivo aditivo) {
        this.aditivo = aditivo;
    }

    public Vigencia getVigencia() {
        return vigencia;
    }

    public void setVigencia(Vigencia vigencia) {
        this.vigencia = vigencia;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public ConvHistorico getHistorico() {
        return historico;
    }

    public void setHistorico(ConvHistorico historico) {
        this.historico = historico;
    }

    public List<Convenio> getLista() {

        List<Convenio> lista = new ArrayList<Convenio>();
        lista.add(convenio);
//      
        return lista;
    }

    public List<Convenio> getListaGeral() {
        ConvenioDAO convenioDAO = new ConvenioDAO();
        List<Convenio> lista = convenioDAO.getConvenios("%");
        //listaConvenios = new ListDataModel(lista);
        return lista;
    }

    @SuppressWarnings("unchecked")
    public DataModel getListaConveniosFederal() {
        ConvenioDAO cDAO = new ConvenioDAO();
        DMlistaConvenios = null;
        if (listaConv != null) {
            DMlistaConvenios = new ListDataModel(listaConv);
        } else {
            listaConv = cDAO.getConvenios("%", TipoConvenio.FEDERAL);
            DMlistaConvenios = new ListDataModel(listaConv);
        }
        return DMlistaConvenios;
    }

    public DataModel getListaConveniosRP() {
        ConvenioDAO cDAO = new ConvenioDAO();
        DMlistaConvenios = null;
        if (listaConv != null) {
            DMlistaConvenios = new ListDataModel(listaConv);
        } else {
            listaConv = cDAO.getConvenios("%", TipoConvenio.RP);
            DMlistaConvenios = new ListDataModel(listaConv);
        }
        return DMlistaConvenios;
    }

    public void localiza() {
        if ("".equals(this.busca)) {
            this.busca = "%";
        }
        ConvenioDAO convenioDAO = new ConvenioDAO();
        listaConv = convenioDAO.getConvenios(this.busca, TipoConvenio.FEDERAL);
        this.busca = new String();
    }

    public void localizarp() {
        if ("".equals(this.busca)) {
            this.busca = "%";
        }
        ConvenioDAO convenioDAO = new ConvenioDAO();
        listaConv = convenioDAO.getConvenios(this.busca, TipoConvenio.RP);
        this.busca = new String();
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;


    }

    public void newConvenio() {

        this.convenio = new Convenio();
        this.convenio.setTipoconvenio(TipoConvenio.FEDERAL);        
        

    }

    public void newConvenioRP(ActionEvent actionEvent) {

        this.convenio = new Convenio();
        this.convenio.setTipoconvenio(TipoConvenio.RP);
        this.contato = new Contato();
        this.vigencia = new Vigencia();
        this.aditivo = new Aditivo();

    }

    public void saveConvenio(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        ConvenioDAO convenioDAO = new ConvenioDAO();
        try {
            convenioDAO.saveConvenio(this.convenio);
            this.convenio = new Convenio();
            this.listaConv = null;

            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }
    }

    public void save() {
        ConvenioDAO convenioDAO = new ConvenioDAO();
        convenioDAO.saveConvenio(convenio);
        
    }

    public void merge() {
        ConvenioDAO convenioDAO = new ConvenioDAO();
        convenioDAO.merge(convenio);
    }

    public void editConvenio() {
        this.contato = new Contato();
        this.vigencia = new Vigencia();
        this.aditivo = new Aditivo();

        ConvenioDAO convenioDAO = new ConvenioDAO();
        Convenio conv = (Convenio) (this.DMlistaConvenios.getRowData());
        this.convenio = convenioDAO.getConvenio(conv.getId());

        this.convenio.getContatos().toString();
        this.convenio.getHistoricos().toString();
        this.convenio.getInvestimentos().toString();
        this.convenio.getVigencias().toString();
        this.convenio.getAditivos().toString();
        




    }
    public void editConvenioView(){
        ConvenioDAO convenioDAO = new ConvenioDAO();
        this.convenio = convenioDAO.getConvenio(this.convenio.getId());
        this.convenio.getAditivos().toString();
        this.convenio.getVigencias().toString();
    }

    public void updateConvenio() {
        context = FacesContext.getCurrentInstance();
        ConvenioDAO convenioDAO = new ConvenioDAO();
        try {

            convenioDAO.updateConvenio(convenio);
            this.listaConv = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelConvenio() {
        this.convenio = new Convenio();
        this.listaConv = null;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Investimento getInvestimento() {
        return investimento;
    }

    public void setInvestimento(Investimento investimento) {
        this.investimento = investimento;
    }

    public void novoContato() {
        this.inserirContato = true;
        this.contato = new Contato();
        this.telefone = new Telefone();
    }

    public void insertContato() {
        ConvenioDAO cdao = new ConvenioDAO();
        if (this.inserirContato == true) {
            this.convenio.getContatos().add(contato);

            this.inserirContato = false;
        }
        cdao.saveConvenio(convenio);


    }

    public void editarContato() {
        this.inserirContato = false;
        this.telefone = new Telefone();
    }

    public void viewContato() {
        ContatoDAO contatoDAO = new ContatoDAO();
        Contato c = contatoDAO.getContato(this.contato.getId());
        this.contato = c;


    }

    public void cancelContato() {
        this.contato = new Contato();
    }

    public void inserirTelefone() {
        this.contato.getTelefones().add(telefone);
        this.telefone = new Telefone();
    }

    public void investNew() {
        investimento = new Investimento();

    }

    public void investAdd() {
        this.investimento.setConvenio(convenio);
        this.convenio.getInvestimentos().add(investimento);
        this.save();
        
        this.investimento = new Investimento();
    }

    public void investCancel() {
        this.investimento = null;
    }

    public void histNew() {
        this.historico = new ConvHistorico();
        anexo = new Anexo();
        inserirHist = true;

    }

    public void histAdd() {
        if (inserirHist == true) {
            
            this.convenio.getHistoricos().add(historico);
            this.historico.setConvenio(convenio);
            inserirHist = false;
        }
        this.save();
        

    }

    public void histEdit() {
        this.inserirHist = false;
        this.anexo = new Anexo();
    }

    public void histCancel() {
        this.historico = null;
    }
    
    public void histIntNew() {
        this.historicoInterno = new HistoricoInterno();
        this.arq = new Arquivo();
        inserirHistInt = true;
    }

    public void histIntAdd() {
        if (inserirHistInt == true) {
            this.historicoInterno.setConvenio(convenio);
            this.convenio.getHistoricoInternos().add(historicoInterno);
            
            inserirHistInt = false;
        }
        this.save();

    }

    public void histIntEdit() {
        this.inserirHistInt = false;
        this.arq = new Arquivo();
    }

    public void histIntCancel() {
        this.inserirHistInt = false;
        this.historicoInterno = null;
    }

    public void vigenciaNew() {
        this.vigencia = new Vigencia();
        this.nvigencia = true;
    }

    public void vigenciaAdd() {
        if (nvigencia == true) {
            
            this.convenio.getVigencias().add(vigencia);
            this.vigencia.setConvenio(convenio);
            this.vigencia = new Vigencia();
            nvigencia = false;
        }
        this.save();
    }

    public void aditivoNew() {
        this.aditivo = new Aditivo();
        this.nadtivo = true;
    }

    public void aditivoAdd() {
        if (nadtivo == true) {
            
            this.convenio.getAditivos().add(aditivo);
            this.aditivo.setConvenio(convenio);
            this.aditivo = new Aditivo();
            nadtivo = false;
        }
        this.save();
        
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            //Cria um arquivo UploadFile, para receber o arquivo do evento
            UploadedFile arqu = event.getFile();
            InputStream in = new BufferedInputStream(arqu.getInputstream());
            //copiar para pasta do projeto
            this.arquivo = caracteres(arqu.getFileName());
            File fille = new File("/home/sgcn/mac/arquivos/" + this.arquivo);

            ///file.mkdirs();
            //O método file.getAbsolutePath() fornece o caminho do arquivo criado
            //Pode ser usado para ligar algum objeto do banco ao arquivo enviado
            
            this.caminho = fille.getAbsolutePath();
            FileOutputStream fout = new FileOutputStream(fille);
            while (in.available() != 0) {
                fout.write(in.read());
            }
            fout.close();
            this.anexoAdd();
            FacesMessage msg = new FacesMessage("Succesful", this.caminho + " foi Enviado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException ex) {
            FacesMessage msg = new FacesMessage("Error", this.caminho + " não Enviado. " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public void handleFileUploadArq(FileUploadEvent event) {
        try {
            //Cria um arquivo UploadFile, para receber o arquivo do evento
            UploadedFile arqu = event.getFile();
            InputStream in = new BufferedInputStream(arqu.getInputstream());
            //copiar para pasta do projeto
            
            this.arquivo = caracteres(arqu.getFileName());
            File fille = new File("/home/sgcn/mac/arquivos/interno/" + arqu.getFileName());

            ///file.mkdirs();
            //O método file.getAbsolutePath() fornece o caminho do arquivo criado
            //Pode ser usado para ligar algum objeto do banco ao arquivo enviado
            
            this.caminho = fille.getAbsolutePath();
            FileOutputStream fout = new FileOutputStream(fille);
            while (in.available() != 0) {
                fout.write(in.read());
            }
            fout.close();
            this.arquivoAdd();
            FacesMessage msg = new FacesMessage("Sucesso", this.caminho + " foi Envidado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException ex) {
            FacesMessage msg = new FacesMessage("Erro", this.caminho + " não Enviado. " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public void fileDownloadController() throws FileNotFoundException {
        //System.out.println("anexo " + anexo.getArquivo());
        // InputStream stream = this.getClass().getResourceAsStream(anexo.getArquivo());
        InputStream stream = new FileInputStream(anexo.getArquivo());
        file = new DefaultStreamedContent(stream, "application/pdf", anexo.getDescricao());
        //System.out.println("nome arquivo : " + file.getName());
        //  System.out.println("arquivo : " + file.getContentType());

    }

    public void fileDownloadInterno() throws FileNotFoundException {
        //System.out.println("anexo " + anexo.getArquivo());
        // InputStream stream = this.getClass().getResourceAsStream(anexo.getArquivo());
        InputStream stream = new FileInputStream(arq.getArquivo());
        file = new DefaultStreamedContent(stream, "application/pdf", arq.getDescricao());
        //System.out.println("nome arquivo : " + file.getName());
        //  System.out.println("arquivo : " + file.getContentType());

    }
    public String caracteres(String texto){
        String ComAcentos = "!@#$%¨&*()-?:{}][ÄÅÁÂÀÃäáâàãÉÊËÈéêëèÍÎÏÌíîïìÖÓÔÒÕöóôòõÜÚÛüúûùÇç ";
        String SemAcentos = "_________________AAAAAAaaaaaEEEEeeeeIIIIiiiiOOOOOoooooUUUuuuuCc_";
        for (int i = 0; i < ComAcentos.length(); i++)
            texto = texto.replace(ComAcentos.charAt(i), SemAcentos.charAt(i)).trim() ;
          //  texto = texto.replace(ComAcentos[i].toString(), SemAcentos[i].toString());
        
        return texto;
        
        //0800 942 0222 marcia fernandes
    }

    public StreamedContent getFile() {
        return this.file;
    }

    public void anexoAdd() {
        try {
            this.anexo.setArquivo(this.caminho);
            this.anexo.setDescricao(this.arquivo);
            this.anexo.setHistorico(historico);

            this.historico.getAnexos().add(anexo);
            this.anexo = new Anexo();

        } catch (Exception e) {
            System.out.println("Erro add anexo");
        }


    }

    public void arquivoAdd() {
        try {
            this.arq.setArquivo(this.caminho);
            this.arq.setDescricao(this.arquivo);
            this.arq.setHistorico(historicoInterno);

            this.historicoInterno.getArquivos().add(arq);
            this.arq = new Arquivo();

        } catch (Exception e) {
            System.out.println("Erro add anexo");
        }


    }
    public void contranew(){
        this.contrapartida = new ContraPartida();
        this.inserirContra = true;
    }
    
    public void contraSave(){
        
        if (this.inserirContra  == true){
            convenio.getContrapartidas().add(contrapartida);
            contrapartida.setConvenio(convenio);
            this.inserirContra = false;
        }
        ConvenioDAO convenioDAO = new ConvenioDAO();
        convenioDAO.saveConvenio(convenio);
        
    }
     public void contraedit(){
        this.inserirContra = false;
    }

    @SuppressWarnings("unchecked")
    public void imprimirDetalhado() throws IOException, JRException {

        //ConvenioDAO cDAO = new ConvenioDAO();
        List<Convenio> listausr = new ArrayList<Convenio>();
        //Convenio c = (Convenio) (this.DMlistaConvenios.getRowData());

        //this.convenio = cDAO.getConvenio(c.getId());  
        listausr.add(convenio);


        FacesContext fcontexts = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontexts.getExternalContext().getContext();
        String relJasper = scontext.getRealPath("/mac/cadastro/convenio/federal/impressao/relaconvdetalhado.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontexts.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=impressao.pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        JasperExportManager.exportReportToPdf(jasperPrint);
        //response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontexts.renderResponse();
        fcontexts.responseComplete();
        //  this.cidadao = null;

    }

    @SuppressWarnings("unchecked")
    public void imprimeTodos() throws IOException, JRException {
        List<Convenio> lista = new ArrayList<Convenio>();
        ConvenioDAO cDAO = new ConvenioDAO();
        lista.addAll(cDAO.getConvenios());

        try {
            FacesContext contexts = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) contexts.getExternalContext().getContext();
            String relJasper = scontext.getRealPath("/mac/cadastro/convenio/federal/impressao/relaconvenios.jasper");
            HttpServletResponse response = (HttpServletResponse) contexts.getExternalContext().getResponse();
            ServletOutputStream responseStream = response.getOutputStream();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
            Map parameters = new HashMap();
            response.setHeader("Content-Disposition", "inline; filename=impressao.pdf");
            response.setContentType("application/pdf");
            response.setHeader("Cache-Control", "no-cache");
            JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
            JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
            responseStream.flush();
            responseStream.close();
            contexts.renderResponse();
            contexts.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
