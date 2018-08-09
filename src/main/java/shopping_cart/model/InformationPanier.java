package shopping_cart.model;

import java.util.ArrayList;
import java.util.List;

public class InformationPanier {
	
	private int numéroCommande;
	
	private InformationClient informationClient;
	
	private final List<InformationProduitLignePanier> listPaniers = new ArrayList<InformationProduitLignePanier> ();
	
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

	public List<InformationProduitLignePanier> getInformationLignePaniers() {
		return listPaniers;
	}
	
	private InformationProduitLignePanier trouverProduitParCode(String code) {
		for (InformationProduitLignePanier ligne : listPaniers) {
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
			this.listPaniers.add(produit);
		}
		int nouvelleQuantité = produit.getQuantité() + quantité;
		if(nouvelleQuantité <= 0) {
			this.listPaniers.remove(produit);
		}else {
			produit.setQuantité(nouvelleQuantité);
		}
	}
	
	public void validation () {
		
	}
	
	public void updateProduct(String code, int quantité) {
		InformationProduitLignePanier produitLignePanier = this.trouverProduitParCode(code);
		if (produitLignePanier != null) {
			if (quantité <= 0) {
				this.listPaniers.remove(produitLignePanier);
			}else {
				produitLignePanier.setQuantité(quantité);
			}
		}
	}
	
}
