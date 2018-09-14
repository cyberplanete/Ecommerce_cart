package ecommerce_panier.dao;

import ecommerce_panier.entity.Produit;
import ecommerce_panier.model.InformationProduit;
import ecommerce_panier.model.PaginationResultat;

public interface ProduitDAO {

	public Produit findProduit(String code);
	
	public InformationProduit findInformationProduit(String code);
	
	public PaginationResultat<InformationProduit> requetteProduit(int page,
             int maxResult, int maxNavigationPage  );

	public PaginationResultat<InformationProduit> requetteProduit(int page, int maxResult,int maxNavigationPage, String likeName);

	public void save(InformationProduit productInfo);
}
