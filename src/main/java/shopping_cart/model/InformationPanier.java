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

}
