package shopping_cart.dao;

import shopping_cart.entity.Produit;
import shopping_cart.model.InformationProduit;
import shopping_cart.model.PaginationResultat;

public interface ProduitDAO {

	public Produit findProduit(String code);
	
	public InformationProduit findInformationProduit(String code);
	
	public PaginationResultat<InformationProduit> requetteProduit(int page,
             int maxResult, int maxNavigationPage  );

	public PaginationResultat<InformationProduit> requetteProduit(int page, int maxResult,int maxNavigationPage, String likeName);

	public void save(InformationProduit productInfo);
}
