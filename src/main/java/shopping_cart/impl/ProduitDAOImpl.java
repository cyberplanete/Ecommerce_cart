package shopping_cart.impl;

import shopping_cart.dao.ProduitDAO;
import shopping_cart.entity.Produit;
import shopping_cart.model.InformationProduit;
import shopping_cart.model.PaginationResultat;

public class ProduitDAOImpl implements ProduitDAO {

	@Override
	public Produit findProduit(String code) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public InformationProduit findInformationProduit(String code) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public PaginationResultat<InformationProduit> requetteProduit(int page, int maxResult, int maxNavigationPage) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public PaginationResultat<InformationProduit> requetteProduit(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public void save(InformationProduit productInfo) {
		// TODO Stub de la méthode généré automatiquement
		
	}

}
