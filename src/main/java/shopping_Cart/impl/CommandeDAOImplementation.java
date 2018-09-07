package shopping_Cart.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import shopping_cart.dao.CommandeDAO;
import shopping_cart.dao.ProduitDAO;
import shopping_cart.entity.Commande;
import shopping_cart.model.InformationCommande;
import shopping_cart.model.InformationDetailCommande;
import shopping_cart.model.InformationPanier;
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
		
		
		
		
		
	}

	private int getMaxNumeroCommande() {
		// TODO Stub de la méthode généré automatiquement
		return 0;
	}

	@Override
	public PaginationResultat<InformationCommande> listInformationCommande(int page, int resultatMax,
			int maxNavigationPage) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public InformationCommande getInformationDeCommande(String commandeID) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public List<InformationDetailCommande> listCommandeDetailInfos(String orderID) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}
	
	
	

}
