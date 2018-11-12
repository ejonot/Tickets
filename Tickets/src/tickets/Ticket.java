package tickets;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Ticket {
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public Gravite getGravite() {
		return gravite;
	}
	public void setGravite(Gravite gravite) {
		this.gravite = gravite;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public Ticket() {}
	public Ticket(Integer id, Gravite gravite, String titre) {
		setId(id);
		setGravite(gravite);
		setTitre(titre);
		setCreation(new Date());
	}
	
	private Integer id;
	private Etat etat;
	private Gravite gravite;
	private String titre;
	private String texte;
	private Date creation;

}
