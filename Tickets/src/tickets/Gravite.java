package tickets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gravite {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	public Gravite() {}
	public Gravite(Integer id, String texte, String couleur) {
		setId(id);
		setTexte(texte);
		setCouleur(couleur);
	}
	private Integer id;
	private String texte;
	private String description;
	private String couleur;
}
