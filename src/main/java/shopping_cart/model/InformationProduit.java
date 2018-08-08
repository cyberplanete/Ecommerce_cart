package shopping_cart.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import shopping_cart.entity.Produit;

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
		this.code = produit.getCode();
		this.nom = produit.getNom();
		this.prix = produit.getPrix();
	}
	
	public int getPrix() {
		// TODO Stub de la méthode généré automatiquement
		return 0;
	}

}
