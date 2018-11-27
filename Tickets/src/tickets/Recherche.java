package tickets;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.jonot.ice.Database;


@ManagedBean
@SessionScoped
public class Recherche {
	public List<Etat> getEtats() {
		return etats;
	}
	public void setEtats(List<Etat> etats) {
		this.etats = etats;
	}
	public List<Gravite> getGravites() {
		return gravites;
	}
	public void setGravites(List<Gravite> gravites) {
		this.gravites = gravites;
	}
	
	public Ticket getSelectedTicket() {
		return selectedTicket;
	}
	public void setSelectedTicket(Ticket selectedTicket) {
		this.selectedTicket = selectedTicket;
	}
	public List<Ticket> getTickets() {
		/*List<Ticket> tickets=new ArrayList<Ticket>() ;
		Ticket test=new Ticket( gravites.get(0), "test");
		test.setEtat(etats.get(0));
		tickets.add(test );
		return tickets;*/
		return Database.getTickets();
	}

	
	@PostConstruct
	public void init() {
		etats=new ArrayList<Etat>();
		
		etats.add(new Etat(0,"Ouvert", "\\f10c" ));
		etats.add(new Etat(1,"Affecté", "\\f04b" ));
		etats.add(new Etat(2,"En pause", "\\f04c" ));
		etats.add(new Etat(3,"Fermé", "\\f096" ));
		etats.add(new Etat(4,"Résolu", "\\f046" ));
		
		
		gravites=new ArrayList<Gravite>();
		
		gravites.add(new Gravite(1,"Information", "#b5bbff"));
		gravites.add(new Gravite(2,"Intervention", "#a4eab7" ));
		gravites.add(new Gravite(3,"Incident", "#f7c96f"));
		gravites.add(new Gravite(4,"Urgence", "#f2a99d"));
	}
	
	private List<Etat> etats;
	private List<Gravite> gravites;
	private Ticket selectedTicket;
	
}
