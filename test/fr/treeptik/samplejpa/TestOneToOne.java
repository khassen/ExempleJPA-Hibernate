package fr.treeptik.samplejpa;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.samplejpa.model.Client;
import fr.treeptik.samplejpa.model.Contrat;

@RunWith(JUnit4.class)
public class TestOneToOne {

	private static EntityManager entityManager = Persistence
			.createEntityManagerFactory("basesamplejpa").createEntityManager();

	@Test
	@Ignore
	public void testSaveCotrat() {

		Client client = new Client(null, "clientSaveContrat",
				"clientSaveContrat", new Date());

		Contrat contrat = new Contrat(null, "refContrat", client);

		entityManager.getTransaction().begin();

		entityManager.persist(client);

		entityManager.persist(contrat);

		entityManager.getTransaction().commit();

	}

	@Test
	@Ignore
	public void testFindContrat() {
		Contrat contrat = entityManager.find(Contrat.class, 2);

		// ona mis fetchType. lazy dans la classe contrat, ca va faire 2
		// requetes pour chercher le contrat avec sans client(via un proxy)
		System.out.println("Ref client : " + contrat.getReferenceContrat());
		System.out.println("Nom client : " + contrat.getClient().getNom());

	}

	// solution: nos relation on la mettera on lazy et on fait une requete

	@Test
	public void testFindQuery() {

		// fetch permet de rapatrier le client

		TypedQuery<Contrat> query = entityManager
				.createQuery("select c from Contrat c Join c.client where c.id= :id", Contrat.class);
		
		query.setParameter("id", 2);
		Contrat contrat = query.getSingleResult();
		
		System.out.println("Ref client : " + contrat.getReferenceContrat());
		System.out.println("Nom client : " + contrat.getClient().getNom());
		

	}
}
