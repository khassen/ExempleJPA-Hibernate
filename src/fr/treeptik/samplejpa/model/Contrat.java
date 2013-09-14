package fr.treeptik.samplejpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Contrat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String referenceContrat;
	
	//quand je recupere un contrat il recupere pas les données d'un client
	@OneToOne(fetch=FetchType.LAZY)
	//@OneToOne
	@JoinColumn(name="client_id")
	private Client client;

	public Contrat(Integer id, String referenceContrat, Client client) {
		super();
		this.id = id;
		this.referenceContrat = referenceContrat;
		this.client = client;
	}

	public Contrat() {
		super();
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferenceContrat() {
		return referenceContrat;
	}

	public void setReferenceContrat(String referenceContrat) {
		this.referenceContrat = referenceContrat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
