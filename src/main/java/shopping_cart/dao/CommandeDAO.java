package shopping_cart.dao;

import java.util.List;

import shopping_cart.model.InformationCommande;
import shopping_cart.model.InformationDetailCommande;
import shopping_cart.model.InformationPanier;
import shopping_cart.model.PaginationResultat;

public interface CommandeDAO {

	public void sauvegardeCommande(InformationPanier informationPanier);
	
	public PaginationResultat<InformationCommande> listInformationCommande(int page, int resultatMax, int maxNavigationPage);
	
	public InformationCommande getInformationDeCommande(String commandeID);
	
	public List<InformationDetailCommande> listCommandeDetailInfos(String orderID);
	
}
