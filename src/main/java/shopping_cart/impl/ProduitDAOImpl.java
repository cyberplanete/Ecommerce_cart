package shopping_cart.impl;

import java.awt.Image;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Null;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import shopping_cart.dao.ProduitDAO;
import shopping_cart.entity.Commande;
import shopping_cart.entity.Compte;
import shopping_cart.entity.Produit;
import shopping_cart.model.InformationProduit;
import shopping_cart.model.PaginationResultat;

@Transactional
public class ProduitDAOImpl implements ProduitDAO {

	@Autowired
	private Session session;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public Produit findProduit(String code) {
		
		// Obtenir la session en cours d'hibernate
				session = sessionFactory.openSession();

				// Creation du Critère
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Produit> criteriaQuery = builder.createQuery(Produit.class);
				
				Root<Produit> produit = criteriaQuery.from(Produit.class);
				criteriaQuery.select(produit);
				criteriaQuery.where(builder.equal(produit.get("ID"), code));
				Query<Produit> maQuery = session.createQuery(criteriaQuery);
				Produit unProduit = maQuery.getSingleResult();
				return unProduit ;
	
	}

	@Override
	public void save(InformationProduit informationProduit) {
		String code = informationProduit.getCode();
		
		Produit produit = null;
		
		Boolean estNouveau = false;
		if (code != null) {
			produit = this.findProduit(code);
		}
		if (produit == null) {
			estNouveau = true;
			produit = new Produit();
			produit.setCreateDate(new Date());
		}
		
		produit.setCode(code);
		produit.setNom(informationProduit.getNom());
		produit.setPrix(informationProduit.getPrix());
		//Enregistrement de l'image
		if (informationProduit.getFichierData() != null) {
			byte[] image = informationProduit.getFichierData().getBytes();
			if (image != null && image.length > 0) {
				produit.setImage(image);
			}
		}
		if (estNouveau) {
			this.sessionFactory.getCurrentSession().persist(produit);
		}
		
		this.sessionFactory.getCurrentSession().flush();
		
	}
	
	
	@Override
	public InformationProduit findInformationProduit(String code) {
		Produit produit = this.findProduit(code);
		
		if (produit == null) {
			return null;
		}
		
		return new InformationProduit(produit.getCode(),produit.getNom(),produit.getPrix());
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

	

}
