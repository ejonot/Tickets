package tickets;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;

@ManagedBean
@ViewScoped

@Entity
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
	public Ticket(Gravite gravite, String titre) {
		setGravite(gravite);
		setTitre(titre);
		setCreation(new Date());
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "etat_id",
	foreignKey = @ForeignKey(name = "ETAT_ID_FK")
	)
	private Etat etat;
	@ManyToOne
	@JoinColumn(name = "gravite_id",
	foreignKey = @ForeignKey(name = "GRAVITE_ID_FK")
	)
	private Gravite gravite;
	private String titre;
	private String texte;
	private Date creation;

}
