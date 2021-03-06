package ecommerce_panier.validateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ecommerce_panier.dao.ProduitDAO;
import ecommerce_panier.entity.Produit;
import ecommerce_panier.model.InformationProduit;

public class InformationProduitValidateur implements Validator {

	   @Autowired
	    private ProduitDAO produitDAO;
	 
	    // Validateur de la classe information produit.
	    @Override
	    public boolean supports(Class<?> clazz) {
	        return clazz == InformationProduit.class;
	    }
	 
	    @Override
	    public void validate(Object target, Errors erreurs) {
	    	InformationProduit informationProduit = (InformationProduit) target;
	 
	        // Vérification des champs pour la class information produit.
	    	//Les messages d'erreurs sont configurés dans le fichier messages/validator.properties
	        ValidationUtils.rejectIfEmptyOrWhitespace(erreurs, "code", "NotEmpty.formProduit.code");
	        ValidationUtils.rejectIfEmptyOrWhitespace(erreurs, "nom", "NotEmpty.formProduit.nom");
	        ValidationUtils.rejectIfEmptyOrWhitespace(erreurs, "prix", "NotEmpty.formProduit.prix");
	 
	        String code = informationProduit.getCode();
	        if (code != null && code.length() > 0) {
	            if (code.matches("\\s+")) {
	                erreurs.rejectValue("code", "Pattern.formProduit.code");
	            } else if(informationProduit.isNewProduit()) {
	                Produit product = produitDAO.findProduit(code);
	                if (product != null) {
	                    erreurs.rejectValue("code", "Duplicate.formProduit.code");
	                }
	            }
	        }
	    }
	 
	}
