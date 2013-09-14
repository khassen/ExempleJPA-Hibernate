package fr.treeptik.samplejpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Commande implements Serializable {

	private static final long serialVersion = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Temporal(TemporalType.DATE)
	private Date dateCommande;
	
	private Double tarif;

	@ManyToOne
	private Client client;
	
	@ManyToMany
	//c pas obligatoire
	@JoinTable(name="CommandesProduits", joinColumns=@JoinColumn(name="commandeId"),inverseJoinColumns=@JoinColumn(name="produitId"))
	private List<Produit> produits;

	public Commande() {
		super();
	}



	public Commande(Integer id, Date dateCommande, Double tarif) {
		super();
		Id = id;
		this.dateCommande = dateCommande;
		this.tarif = tarif;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Double getTarif() {
		return tarif;
	}

	public void setTarif(Double tarif) {
		this.tarif = tarif;
	}

	public static long getSerialversion() {
		return serialVersion;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}



	public List<Produit> getProduits() {
		return produits;
	}



	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
