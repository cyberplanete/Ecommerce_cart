package shopping_cart.validateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import shopping_cart.dao.ProduitDAO;
import shopping_cart.entity.Produit;
import shopping_cart.model.InformationProduit;

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
	 
	        // Check the fields of ProductInfo class.
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
