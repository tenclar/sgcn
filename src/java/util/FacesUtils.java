/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;

/**
 *
 * @author tecnologia01
 */

//@Component
//@Scope("request")
public class FacesUtils implements  Serializable {
    private  RequestContext requestContext = RequestContext.getCurrentInstance();  
    public  String i18nMensagem(String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "i18n");
        return bundle.getString(mensagem);
    }
    
    public void addCallback(String param, boolean var){
        requestContext.addCallbackParam(param, var);
    }
    
    public  void info(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem));
    }

    public  void aviso(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atenção", mensagem));
    }

    public  void erro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", mensagem));
    }

    public  void fatal(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal", mensagem));
    }
    
    public void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);                        
			evh.setValid(true);                        
                        
		}
                if (component instanceof UIData) {
                    UIData uidata = (UIData) component;
                    uidata.clearInitialState();
                    
                }
                if (component instanceof InputText) {
                    InputText it =(InputText) component;
                    it.setAutocomplete("off");
                }
                
		if(component.getChildCount()>0){
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}

}
