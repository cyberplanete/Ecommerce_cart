package ecommerce_panier.util;

import javax.servlet.http.HttpServletRequest;

import ecommerce_panier.model.InformationPanier;

public class Utilitaires {
	
	
	//Articles dans le panier , enregistré dans la session
	public static InformationPanier getInformationPanierDansLaSession(HttpServletRequest request) {
		
		//Obtenir le panier depuis la session
		InformationPanier informationPanier = (InformationPanier) request.getSession().getAttribute("monPanier");
		
		//Si null, le créer
		if (informationPanier == null) {
			informationPanier = new InformationPanier();
			
			//et l'enregistrer dans la session
			request.getSession().setAttribute("monPanier", informationPanier);
		}
		
		
		return informationPanier;
	}

	
	 public static void removePanierDansSession(HttpServletRequest request) {
	        request.getSession().removeAttribute("monPanier");
	    }
	 
	    public static void enregistrerDerniereCommandePanierDansSession(HttpServletRequest request, InformationPanier cartInfo) {
	        request.getSession().setAttribute("dernièreCommandePanier", cartInfo);
	    }
	    
	    public static InformationPanier getDerniereCommandePanierDansSession(HttpServletRequest request) {
	        return (InformationPanier) request.getSession().getAttribute("DerniereCommandePanier");
	    }
	 
	
	
	
}
