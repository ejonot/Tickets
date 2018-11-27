package tickets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Etat {

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
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	
	public  Etat() {}
	public  Etat(Integer id, String texte, String icone) {
		setId(id);
		setTexte(texte);
		setIcone(icone);
	}
	
	private Integer id;
	private String texte;
	private String description;
	private String icone;
	
}
