package shopping_cart.model;

import java.util.ArrayList;
import java.util.List;

public class InformationPanier {
	
	private int numéroCommande;
	
	private InformationClient informationClient;
	
	private final List<InformationLignePanier> informationLignePaniers = new ArrayList<InformationLignePanier> ();
	
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

	public List<InformationLignePanier> getInformationLignePaniers() {
		return informationLignePaniers;
	}
	
	private InformationLignePanier trouverLigneParCode(String Code) {
		for (InformationLignePanier ligne : informationLignePaniers) {
			if (ligne.getInfoProduit().getCode().equals(Code)) {
				return ligne;
			}
		}
		return null;
	}
	
	//Si Produit inexistant ou pas en stock, le produit n'apparait pas dans le panier
	public void ajouterProduit(InformationProduit informationProduit , int quantité) {
		InformationLignePanier ligneProduit = this.trouverLigneParCode(informationProduit.getCode());
		
		if (ligneProduit == null) {
			ligneProduit = new InformationLignePanier();
			ligneProduit.setQuantité(0);
			ligneProduit.setInfoProduit(informationProduit);
			this.informationLignePaniers.add(ligneProduit);
		}
	}
	
}
