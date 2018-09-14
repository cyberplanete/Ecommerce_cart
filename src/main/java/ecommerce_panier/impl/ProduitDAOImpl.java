package ecommerce_panier.impl;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ecommerce_panier.dao.ProduitDAO;
import ecommerce_panier.entity.Produit;
import ecommerce_panier.model.InformationProduit;
import ecommerce_panier.model.PaginationResultat;

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
		
				
		return requetteProduit(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResultat<InformationProduit> requetteProduit(int page, int maxResult, int maxNavigationPage,
			String commeNom) {
		String sql = "Select new "  + InformationProduit.class.getName()
				+ "(p.code ,  p.nom, p.prix) " + "from " 
				+ Produit.class.getName() + "p" ;
		if (commeNom != null && commeNom.length() > 0) {
			sql += "Where lower(p.nom) like : commeNom" ;
		}
		sql += " order by p.createDate desc";
		
		session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(sql);
		
		if (commeNom != null && commeNom.length() > 0) {
			query.setParameter("commeNom", "%"+commeNom.toLowerCase() + "%");
		}
		return new PaginationResultat<InformationProduit>(query,page,maxResult,maxNavigationPage);
	}

	

}
