package ecommerce_panier.dao;

import java.util.List;

import ecommerce_panier.model.InformationCommande;
import ecommerce_panier.model.InformationDetailCommande;
import ecommerce_panier.model.InformationPanier;
import ecommerce_panier.model.PaginationResultat;

public interface CommandeDAO {

	public void sauvegardeCommande(InformationPanier informationPanier);
	
	public PaginationResultat<InformationCommande> listInformationCommande(int page, int resultatMax, int maxNavigationPage);
	
	public InformationCommande getInformationDeCommande(String commandeID);
	
	public List<InformationDetailCommande> listCommandeDetailInfos(String orderID);
	
}
