package shopping_cart.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import shopping_cart.entity.Compte;

//Transactionnel pour Hibernate

@Transactional
public class CompteDAOImplementation implements CompteDAO{


	@Autowired
	private Session session;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Compte trouverCompte(String nomUtilisateur) {
		
		// Obtenir la session en cours d'hibernate
		session = sessionFactory.openSession();
		
		// Obtenir la session en cours d'hibernate
				session = sessionFactory.openSession();

				// Creation du Critère
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Compte> criteriaQuery = builder.createQuery(Compte.class);
				
				Root<Compte> clients = criteriaQuery.from(Compte.class);
				criteriaQuery.select(clients);
				criteriaQuery.where(builder.equal(clients.get("Utilisateur_Nom"), nomUtilisateur));
				Query<Compte> maQuery = session.createQuery(criteriaQuery);
				Compte leClient = maQuery.getSingleResult();
				//
				// //Créer une requete sur la table Comptes pour obtenir la liste
				// Query<Compte> uneRequete = sessionEnCours.createQuery(idClientSQL,
				// Compte.class);
				//
				// //Obtenir ke resultat final
				// Compte leClientClass = uneRequete.getSingleResult();

				session.close();
				return leClient;
	}

}
