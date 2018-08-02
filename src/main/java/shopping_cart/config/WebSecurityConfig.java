package shopping_cart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	  @Autowired
	   MonServiceAuthenticationDB monServiceAuthenticationDB;
	 
	   @Autowired
	   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 
	       // For User in database.
	       auth.userDetailsService(monServiceAuthenticationDB);
	 
	   }
	 
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	 
	       //Protection cross-site request forgery désactivée
		   http.csrf().disable();
	 

	    // Les pages nécessitent une connexion en tant que EMPLOYEE ou MANAGER.
	    // S'il n'y a pas de login, Redirection vers la page / login.
	       http.authorizeRequests().antMatchers("/orderList","/order", "/accountInfo")//
	               .access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");
	 
	       // Pour MANAGER seulement.
	       http.authorizeRequests().antMatchers("/produit").access("hasRole('ROLE_MANAGER')");
	 

	    // Lorsque l'utilisateur s'est connecté en tant que XX.
	    // Mais accéde à une page qui nécessite le rôle YY,
	    // AccessDeniedException .
	       http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	 
	       // Configuration pour Login Form
	       http.authorizeRequests().and().formLogin()//
	               // Submit URL of login page.
	               .loginProcessingUrl("/j_spring_security_check") // Submit URL
	               .loginPage("/login")//
	               .defaultSuccessUrl("/accountInfo")//
	               .failureUrl("/login?error=true")//
	               .usernameParameter("userName")//
	               .passwordParameter("password")
	               // Config for Logout Page
	               // (Go to home page).
	               .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	 
	   }
	}