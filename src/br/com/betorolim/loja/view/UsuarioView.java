package br.com.betorolim.loja.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import javafx.scene.control.TableColumn.CellEditEvent;


public class UsuarioView {
	
	public void EditaLinha(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Car Edited", null);
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void cancelaEdicao(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Edit Cancelled", null);
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
