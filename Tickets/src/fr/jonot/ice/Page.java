package fr.jonot.ice;

import java.util.ArrayList;
//classe pour "encapsuler" une page xhtml
import java.util.List;

public class Page {
  public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		if(titre!=null && !titre.isEmpty()){
			int indexFin=titre.indexOf(".");
			if(indexFin==-1) indexFin=titre.length();
			this.titre=titre.substring(0, indexFin);
		}	
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<String> getAutorisations() {
		return autorisations;
	}
	public void setAutorisations(List<String> autorisations) {
		if(autorisations!=null){
			for(String a : autorisations) addAutorisation(a);
		}
	}
	public void addAutorisation(String autorisation) {
		if(autorisations==null) autorisations=new ArrayList<String>();
		if(!autorisations.contains(autorisation)) autorisations.add(autorisation);

	}
	public List<String> getMetadata() {
		return metadata;
	}
	public void setMetadata(List<String> metadata) {
		this.metadata = metadata;
	}
	
	public Page (String titre){
			setTitre(titre);
	}
	
	public boolean equals(Object o){
		if(o==null) return false;
		Page p=(Page)o;
		if(p==null || !p.getTitre().equals(getTitre()))return false;
		return true;
	}
	
private String titre;
  private String url;
  private String parent;
  private List<String> autorisations;
  private List<String> metadata;
  
  
}
