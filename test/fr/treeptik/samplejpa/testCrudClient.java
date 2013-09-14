package fr.treeptik.samplejpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.samplejpa.model.Client;

@RunWith(JUnit4.class)
public class testCrudClient {

	private EntityManager entityManager = Persistence
			.createEntityManagerFactory("basesamplejpa").createEntityManager();

	@Test
	@Ignore
	public void testSaveOk() {

		Client client = new Client(null, "client1", "client1", new Date());
		entityManager.getTransaction().begin();
		entityManager.persist(client);
		entityManager.getTransaction().commit();
	}

	@Test
	@Ignore
	public void testRemove() {

		Client client1 = entityManager.find(Client.class, 1);
		System.out.println(client1.getNom());
		entityManager.getTransaction().begin();
		entityManager.remove(client1);
		entityManager.getTransaction().commit();
	}

	@Test
	@Ignore
	public void testUpdate() {

		Client client1 = entityManager.find(Client.class, 2);

		entityManager.getTransaction().begin();

		client1.setNom("cccl");
		client1.setPrenom("prrr");

		// on prefere la methode merge

		// entityManager.merge(client1);

		entityManager.getTransaction().commit();
	}

	@Test
	@Ignore
	public void testMerge() {

		Client client = new Client(2, "Client", "lolo", new Date());

		entityManager.getTransaction().begin();

		entityManager.merge(client);

		entityManager.getTransaction().commit();

	}

	// @SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void testQuary() {

		TypedQuery<Client> query = entityManager.createQuery(
				"select c from Client c", Client.class);
		List<Client> clients = query.getResultList();

		for (Client client : clients) {

			System.out.println(client.getNom());

		}
	}

	@Test
	@Ignore
	public void testFindByName() {

		TypedQuery<Client> query = entityManager.createQuery(
				"select c from Client c where c.nom= :nom", Client.class);
		query.setParameter("nom", "client1");

		List<Client> clients = query.getResultList();

		for (Client client : clients) {

			System.out.println(client.getNom());

		}
	}

	@Test
	@Ignore
	public void testQuery2() {

		Query query = entityManager
				.createQuery("select c.id, c.nom from Client c where c.nom= :nom");
		query.setParameter("nom", "client1");

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();

		for (Object[] objects : resultList) {

			System.out.println("ID :" + objects[0] + "nom :" + objects[1]);

		}
	}

	@Test
	@Ignore
	public void testQuery3() {

		TypedQuery<Client> query = entityManager.createQuery(
				"select c from Client c where c.id= :id", Client.class);
		query.setParameter("id", 4);

		Client client = query.getSingleResult();
		System.out.println(client);
	}

	
	//si on veut faire un update sur plusieurs objets au meme temps
	
	@Test
	@Ignore
	public void testQuery4() {

		Query query = entityManager.createQuery(
				"Update Client c SET c.prenom= :prenom where c.nom= :nom");
		
		
		query.setParameter("prenom", "prenom");
		query.setParameter("nom", "client1");

		entityManager.getTransaction().begin();
		
		query.executeUpdate();
		
		entityManager.getTransaction().commit();
	}
	
	//si on veut faire un delete sur plusieurs objets au meme temps

	@Test
	public void testQuery5() {

		Query query = entityManager.createQuery(
				"delete Client c where c.nom= :nom");

		query.setParameter("nom", "client");

		entityManager.getTransaction().begin();
		
		query.executeUpdate();
		
		entityManager.getTransaction().commit();
	}

}
