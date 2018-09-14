package ecommerce_panier.model;

import java.util.Date;
import java.util.List;

import ecommerce_panier.entity.CommandeDetail;

public class InformationCommande {

	private String id;
	private Date dateDeCommande;
	private int numéroCommande;
	private double montant;
	
	private String nomClient;
	private String rueClient;
	private String codePostalClient;
	private String téléphoneClient;
	
	

	private List<CommandeDetail>listdetailsCommande;
	
	public InformationCommande() {
		
	}
//Hibernate Query
	public InformationCommande(String id, Date dateDeCommande, int numéroCommande, double montant, String nomClient,
			String rueClient, String codePostalClient, String téléphoneClient) {
		super();
		this.id = id;
		this.dateDeCommande = dateDeCommande;
		this.numéroCommande = numéroCommande;
		this.montant = montant;
		this.nomClient = nomClient;
		this.rueClient = rueClient;
		this.codePostalClient = codePostalClient;
		this.téléphoneClient = téléphoneClient;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateDeCommande() {
		return dateDeCommande;
	}

	public void setDateDeCommande(Date dateDeCommande) {
		this.dateDeCommande = dateDeCommande;
	}

	public int getNuméroCommande() {
		return numéroCommande;
	}

	public void setNuméroCommande(int numéroCommande) {
		this.numéroCommande = numéroCommande;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getRueClient() {
		return rueClient;
	}

	public void setRueClient(String rueClient) {
		this.rueClient = rueClient;
	}

	public String getCodePostalClient() {
		return codePostalClient;
	}

	public void setCodePostalClient(String codePostalClient) {
		this.codePostalClient = codePostalClient;
	}

	public List<CommandeDetail> getListdetailsCommande() {
		return listdetailsCommande;
	}

	public void setListdetailsCommande(List<CommandeDetail> listdetailsCommande) {
		this.listdetailsCommande = listdetailsCommande;
	}
	public String getTéléphoneClient() {
		return téléphoneClient;
	}
	public void setTéléphoneClient(String téléphoneClient) {
		this.téléphoneClient = téléphoneClient;
	}
	
}
