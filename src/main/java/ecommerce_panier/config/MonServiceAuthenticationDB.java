package ecommerce_panier.config;

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

public class MonServiceAuthenticationDB implements UserDetailsService{

	@Autowired
	CompteDAO compteDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Compte compte = compteDAO.trouverCompte(username);
        System.out.println("Account= " + compte);
 
        if (compte == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }
 
        // EMPLOYEE,MANAGER,..
        String role = compte.getRoleUtilisateur();
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
 
        // ROLE_EMPLOYEE, ROLE_MANAGER
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
 
        grantList.add(authority);
 
        boolean activé = compte.estActif();
        boolean compteNonExpiré = true;
        boolean identifiantNonExpiré = true;
        boolean compteNonBloqué = true;
 
        UserDetails utilisateurDetails = (UserDetails) new User(compte.getNomUtilisateur(),compte.getPassword(), activé, compteNonExpiré, //
                identifiantNonExpiré, compteNonBloqué, grantList);
 
        return utilisateurDetails;
    }
 
}