package br.com.betorolim.loja.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.betorolim.loja.bean.UsuarioBean;

public class Autorizador implements PhaseListener {

	@Inject
	private UsuarioBean usuarioBean;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		FacesContext context = event.getFacesContext();

		
		/*if("/admin.xhtml".equals(context.getViewRoot().getViewId())){
			System.out.println(usuarioBean.getUsuario().getPerfil());
			if(usuarioBean.getUsuario().getPerfil() == 0){
				NavigationHandler handler = context.getApplication().getNavigationHandler();
				handler.handleNavigation(context, null, "principal?faces-redirect=true");
				
				context.renderResponse();
			}else{
				return;
			}
		}*/
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
