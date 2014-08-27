/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/**
 *
 * @author tenclar
 */
import java.util.ArrayList;

import java.util.List;

import javax.faces.component.UIComponent;

import javax.faces.component.UISelectItems;

import javax.faces.context.FacesContext;

import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import javax.faces.model.SelectItem;

@FacesConverter(value = "IndexedConverter")
public class IndexedConverter implements Converter {

    private int index = 0;
    
    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uicomp, String value) {
        List<SelectItem> items = new ArrayList<SelectItem>();
        
        List<UIComponent> uicompList = uicomp.getChildren();
        for (UIComponent comp : uicompList) {
            if (comp instanceof UISelectItems) {
                items.addAll((List<SelectItem>) ((UISelectItems) comp).getValue());
            }
        }
        return "-1".equals(value) ? 0 : items.get(Integer.valueOf(value)).getValue();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uicomp, Object entity) {
        return entity == null ? "-1" : String.valueOf(index++);
    }
}
