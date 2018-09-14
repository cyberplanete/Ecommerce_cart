package ecommerce_panier.model;

import java.util.ArrayList;
import java.util.List;

public class InformationPanier {
	
	private int numéroCommande;
	
	private InformationClient informationClient;
	
	private final List<InformationProduitLignePanier> listePanier = new ArrayList<InformationProduitLignePanier> ();
	
	public InformationPanier() {
		
	}

	public int getNuméroCommande() {
		return numéroCommande;
	}

	public void setNuméroCommande(int numéroCommande) {
		this.numéroCommande = numéroCommande;
	}

	public InformationClient getInformationClient() {
		return informationClient;
	}

	public void setInformationClient(InformationClient informationClient) {
		this.informationClient = informationClient;
	}

	public List<InformationProduitLignePanier> getInformationLignesPaniers() {
		return listePanier;
	}
	
	private InformationProduitLignePanier trouverProduitParCode(String code) {
		for (InformationProduitLignePanier ligne : listePanier) {
			if (ligne.getInfoProduit().getCode().equals(code)) {
				return ligne;
			}
		}
		return null;
	}
	
	
	public void ajouterProduit(InformationProduit informationProduit , int quantité) {
		InformationProduitLignePanier produit = this.trouverProduitParCode(informationProduit.getCode());
//		 Si produit non-présent dans la liste alors je crée une référence à la nouvelle instance InformationLignePanier
//		 pour permettre d'initialiser ligneProduit
		if (produit == null) {
			produit = new InformationProduitLignePanier();
			produit.setQuantité(0);
			produit.setInfoProduit(informationProduit);
			this.listePanier.add(produit);
		}
		int nouvelleQuantité = produit.getQuantité() + quantité;
		if(nouvelleQuantité <= 0) {
			this.listePanier.remove(produit);
		}else {
			produit.setQuantité(nouvelleQuantité);
		}
	}
	
	public void validation () {
		
	}
	
	public void miseAJourProduit(String code, int quantité) {
		InformationProduitLignePanier produitLignePanier = this.trouverProduitParCode(code);
//		 Si produit non-présent dans la liste aucune action
		if (produitLignePanier != null) {
			if (quantité <= 0) {
				this.listePanier.remove(produitLignePanier);
			}else {
				produitLignePanier.setQuantité(quantité);
			}
		}
	}
	
	public void supprimeProduit(InformationProduit produit) {
		InformationProduitLignePanier produitLignePanier = this.trouverProduitParCode(produit.getCode());
		
		if (produitLignePanier !=null) {
			this.listePanier.remove(produitLignePanier);
		}
		
	}
	
	public boolean isEmpty() {
		return this.listePanier.isEmpty();
	}
	
	public boolean isClientValide() {
		return this.informationClient != null && this.informationClient.isValide();
	}
	
	public int getQuantitéTotale() {
		int quantité = 0 ;
		for (InformationProduitLignePanier produitLignePanier : listePanier) {
			quantité += produitLignePanier.getQuantité();
		}return quantité;
	}
	
	public double getMontantTotale() {
		double total = 0;
		for (InformationProduitLignePanier produitLignePanier : listePanier) {
			total += produitLignePanier.getMontant(); 
		}return total;
	}
	//Mise à jour via formulaire
	public void miseAJourQuantité(InformationPanier formulaireInformationProduit) {
		if (formulaireInformationProduit != null) {
			List<InformationProduitLignePanier> listeDeProduits = formulaireInformationProduit.getInformationLignesPaniers();
			for (InformationProduitLignePanier produit : listeDeProduits) {
				this.miseAJourProduit(produit.getInfoProduit().getCode(),produit.getQuantité());
			}
		}
	}
	
}
