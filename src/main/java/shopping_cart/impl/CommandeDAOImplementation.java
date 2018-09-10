package shopping_cart.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import shopping_cart.dao.CommandeDAO;
import shopping_cart.dao.ProduitDAO;
import shopping_cart.entity.Commande;
import shopping_cart.entity.CommandeDetail;
import shopping_cart.entity.Produit;
import shopping_cart.model.InformationClient;
import shopping_cart.model.InformationCommande;
import shopping_cart.model.InformationDetailCommande;
import shopping_cart.model.InformationPanier;
import shopping_cart.model.InformationProduitLignePanier;
import shopping_cart.model.PaginationResultat;

@Transactional
public class CommandeDAOImplementation implements CommandeDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	// @Transactional, il faut utiliser le tag <annotation-driven> de l'espace de
	// nommage tx pour préciser à Spring 
	// que les annotations sont utilisées pour la définition des transactions.
	// indique au conteneur les méthodes qui doivent s'exécuter dans un context
	// transactionnel.
	// @Transactional. Enlève l'obligation d'ajouter du code tel que
	// Begin transtation , commit transaction….

	// Avec projet_crm_spring_hibernate.xml

	private Session session;
	
	@Autowired
	private ProduitDAO produitDAO;

	@Override
	public void sauvegardeCommande(InformationPanier informationPanier) {
		// Obtenir la session en cours d'hibernate
		session = sessionFactory.openSession();
		
		int numeroCommande =  this.getMaxNumeroCommande() + 1;
		Commande commande = new Commande();
		
		commande.setId(UUID.randomUUID().toString());
		commande.setNuméroDeCommande(numeroCommande);
		commande.setDateDeCommande(new Date());
		commande.setMontant(informationPanier.getMontantTotale());
		
		InformationClient informationClient = informationPanier.getInformationClient();
		commande.setNomClient(informationClient.getNom());
		commande.setEmailClient(informationClient.getEmail());
		commande.setTéléphoneClient(informationClient.getTelephone());
		commande.setAddresseClient(informationClient.getAdresse_rue());
		commande.setCode_postal(informationClient.getAdresse_codePostal());
		
		session.persist(commande);
		
		List<InformationProduitLignePanier> listLignesProduit = informationPanier.getInformationLignesPaniers();
		
		for (InformationProduitLignePanier informationProduitLignePanier : listLignesProduit) {
			CommandeDetail commandeDetail = new CommandeDetail();
			commandeDetail.setId(UUID.randomUUID().toString());
			//id de la commande
			commandeDetail.setCommande(commande);
			commandeDetail.setMontant(informationProduitLignePanier.getMontant());
			commandeDetail.setPrix(informationProduitLignePanier.getInfoProduit().getPrix());
			commandeDetail.setQuantité(informationProduitLignePanier.getQuantité());
			
			String code = informationProduitLignePanier.getInfoProduit().getCode();
			Produit produit = this.produitDAO.findProduit(code);
			commandeDetail.setProduit(produit);
			
			session.persist(commandeDetail);
		}
		informationPanier.setNuméroCommande(numeroCommande);
	}

	private int getMaxNumeroCommande() {
		// TODO Stub de la méthode généré automatiquement
		return 0;
	}

	@Override
	public PaginationResultat<InformationCommande> listInformationCommande(int page, int resultatMax,
			int maxNavigationPage) {
		String sql = "Select new" + InformationCommande.class.getName()
				+ "(Commandes.ID, Commandes.commande_Date,Commandes.commande_Num,Commandes.Montant, "
				+ "Commandes.Client_Nom, Commandes.Client_Adresse, Commandes.Client_Email, Commandes.Client_Téléphone)" + "from"
				+ Commande.class.getName() + "Commandes"//
				+ "order by Commandes.commande_Num desc";
		// Obtenir la session en cours d'hibernate
				session = sessionFactory.openSession();
				Query query = session.createQuery(sql);
				
				
		return new PaginationResultat<InformationCommande>(query,page,resultatMax,maxNavigationPage);
	}

	
	public Commande findCommande(String commandeID) {
		// Obtenir la session en cours d'hibernate
		session = sessionFactory.openSession();

		// Creation du Critère
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Commande> criteriaQuery = builder.createQuery(Commande.class);
		
		
		Root<Commande> commande = criteriaQuery.from(Commande.class);
		criteriaQuery.select(commande);
		criteriaQuery.where(builder.equal(commande.get("ID"), commandeID));
		Query<Commande> maQuery = session.createQuery(criteriaQuery);
		Commande uneCommande = maQuery.getSingleResult();
		return uneCommande ;
	}

	@Override
	public List<InformationDetailCommande> listCommandeDetailInfos(String commandeID) {
		
		String sql = "Select new " + InformationDetailCommande.class.getName()
				+ "(d.id, d.produit_id, d.produit_nom , d.quanitté,d.prix,d.montant) "//
                + " from " + CommandeDetail.class.getName() + " d "//
                + " where d.commandes.id = : commandeID";
		
		// Obtenir la session en cours d'hibernate
				session = sessionFactory.openSession();

				// Creation du Critère
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<InformationDetailCommande> criteriaQuery = builder.createQuery(InformationDetailCommande.class);
		
				Query<InformationDetailCommande> maQuery = session.createQuery(criteriaQuery);
				 maQuery.setParameter("commandeID",commandeID);
		
		return maQuery.list();
	}

	@Override
	public InformationCommande getInformationDeCommande(String commandeID) {
		Commande commande = this.findCommande(commandeID);
		if (commande == null) {
			return null;
		}
		return new InformationCommande(commande.getId(),commande.getDateDeCommande(),//
				commande.getNuméroDeCommande(), commande.getMontant(), commande.getNomClient(),commande.getAddresseClient(),//
				commande.getEmailClient(), commande.getTéléphoneClient());
	}
	
	
	

}
