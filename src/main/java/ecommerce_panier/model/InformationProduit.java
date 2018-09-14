package ecommerce_panier.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ecommerce_panier.entity.Produit;

public class InformationProduit {

	private String code;
	private String nom;
	private double prix;
	
	private boolean newProduit = false;
	
	//Téléversement du fichier
	private CommonsMultipartFile fichierData;
	
	
	public InformationProduit() {
	}
	
	public InformationProduit(Produit produit) {
		this.setCode(produit.getCode());
		this.setNom(produit.getNom());
		this.setPrix(produit.getPrix());
	}
	
	public InformationProduit(String code ,String nom, double prix) {
		this.setCode(code);
		this.setNom(nom);
		this.setPrix(prix);
	}
	
	
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public boolean isNewProduit() {
		return newProduit;
	}

	public void setNewProduit(boolean newProduit) {
		this.newProduit = newProduit;
	}

	public CommonsMultipartFile getFichierData() {
		return fichierData;
	}

	public void setFichierData(CommonsMultipartFile fichierData) {
		this.fichierData = fichierData;
	}

	



}
