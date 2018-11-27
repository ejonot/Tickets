package fr.jonot.ice;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.util.Constants;

@ManagedBean
@RequestScoped
public class Connexion {

	private static String COMMON_TEMPLATE="/WEB-INF/Templates/commun.xhtml";
	private static String SANS_TEMPLATE="/WEB-INF/Templates/sans.xhtml";
	
	public String getTemplate(){
		ExternalContext externalContext =FacesContext.getCurrentInstance().getExternalContext();
		if(externalContext.getRequestParameterMap().containsKey(Constants.DIALOG_FRAMEWORK.CONVERSATION_PARAM)) return SANS_TEMPLATE;
		return COMMON_TEMPLATE;
	}
	
}
