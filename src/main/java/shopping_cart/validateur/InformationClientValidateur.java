package shopping_cart.validateur;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import shopping_cart.model.InformationClient;

public class InformationClientValidateur implements Validator {

	private EmailValidator emailValidateur = EmailValidator.getInstance();
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz == InformationClient.class;
	}

	@Override
	public void validate(Object cible, Errors erreurs) {
		InformationClient informationClient = (InformationClient) cible;
		
		//verification des champs de la classe InformationClient
		ValidationUtils.rejectIfEmptyOrWhitespace(erreurs, "nom", "NotEmpty.formulaireClient.nom");
		ValidationUtils.rejectIfEmptyOrWhitespace(erreurs, "email", "NotEmpty.formulaireClient.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(erreurs, "adresse", "NotEmpty.formulaireClient.adresse");
		ValidationUtils.rejectIfEmptyOrWhitespace(erreurs, "telephone", "NotEmpty.formulaireClient.telephone");
		
		if (!emailValidateur.isValid(informationClient.getEmail())) {
			erreurs.rejectValue("email", "Pattern.formulaireClient.email");
		}
		
	}

	
	
}
