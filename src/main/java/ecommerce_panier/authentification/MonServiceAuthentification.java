package ecommerce_panier.authentification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ecommerce_panier.dao.CompteDAO;
import ecommerce_panier.entity.Compte;

public class MonServiceAuthentification implements UserDetailsService{
	
	@Autowired
	private CompteDAO compteDAO;

	@Override
	public UserDetails loadUserByUsername(String nomUtilisateur) throws UsernameNotFoundException {
		Compte compte = compteDAO.trouverCompte(nomUtilisateur);
		System.out.println("Compte=" + compte);
		
		if (compte == null) {
			throw new UsernameNotFoundException("Utilisateur"//
					+ nomUtilisateur + " non trouvé dans la base de donnée");
		}
		// Employée, Manager ...
		String role = compte.getRoleUtilisateur();
		
		List<GrantedAuthority> listUtilisateurAutorise = new ArrayList<GrantedAuthority>();
		
		
		//ROLE_EMPLOYEE , ROLE_MANAGER
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		
		listUtilisateurAutorise.add(authority);
		
		boolean actif = compte.estActif();
		boolean compteNonExpire = true;
		boolean credentialsNonExpire = true;
		boolean compteNonBloqué = true;
		
		UserDetails userDetails = (UserDetails) new User(compte.getNomUtilisateur(),
				compte.getPassword(), actif, compteNonExpire, credentialsNonExpire, compteNonBloqué, listUtilisateurAutorise);
		
		return userDetails;
	}

}
