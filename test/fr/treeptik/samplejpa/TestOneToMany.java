package fr.treeptik.samplejpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.samplejpa.model.Client;
import fr.treeptik.samplejpa.model.Commande;

@RunWith(JUnit4.class)
public class TestOneToMany {
	
	private static EntityManager entityManager = Persistence
			.createEntityManagerFactory("basesamplejpa").createEntityManager();

	@Test
	@Ignore
	public void saveCommande(){
		
		Commande commande = new Commande(null, new Date(),50.50);
		
		commande.setClient(entityManager.find(Client.class, 4));
		
		entityManager.getTransaction().begin();

		entityManager.persist(commande);
		System.out.println(commande.getId());

		entityManager.getTransaction().commit();
	}
	
	@Test
	public void find(){
		
		// pour voir ce qui fait l'entityManager et supprimer ce qui il a dans sa memoire: etre sur qui il va a la base.
		entityManager.clear();
		
		Client client = entityManager.find(Client.class, 4);
		
		//System.out.println( client.getCommandes().size());
		
		for (Commande commandes : client.getCommandes()) {
			
			System.out.println(commandes.getId() + " " + commandes.getTarif());
			
		}
	}

}
