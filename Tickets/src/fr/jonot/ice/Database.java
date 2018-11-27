package fr.jonot.ice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tickets.Ticket;

public class Database {

	
	 private static final String PERSISTENCE_UNIT_NAME = "TicketJPA";   
	 private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	 private static EntityTransaction transac = em.getTransaction();
	 
	 public static  List<Ticket> getTickets(){
		 
		 Query q = em.createQuery("select t from Ticket t");
	        List<Ticket> tickets = q.getResultList();
	        return tickets;
	 }
	 
	 public static boolean save(Ticket ticket){
		 	transac.begin();
	        em.persist(ticket);
	        transac.commit();
	        em.close();
	        return true;
	 }
	 
	 public static Ticket getTicket(Integer id){
		 return new Ticket();
	 }
	 
}
