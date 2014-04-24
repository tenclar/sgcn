/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author tenclar
 */
public class StringAsUpperConverter implements Converter {
    
    @Override
     public Object getAsObject(FacesContext facesContext , UIComponent component, String value) {  
            return value.toString().toUpperCase();  
            //return (String) value; // Or (value != null) ? value.toString().toUpperCase() : null;
  }  
   
    @Override
  public String getAsString(FacesContext facesContext  , UIComponent component, Object value) {  
   return value.toString().toUpperCase();  
   //return (value != null) ? value.toUpperCase() : null;
  }  
    
}
