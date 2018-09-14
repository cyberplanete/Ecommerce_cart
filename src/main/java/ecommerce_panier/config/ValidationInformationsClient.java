package ecommerce_panier.config;



import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ecommerce_panier.validateur.InformationClientValidateur;

public class ValidationInformationsClient implements Validator{

	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Stub de la méthode généré automatiquement
		return clazz == InformationClientValidateur.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		InformationClientValidateur informationClient = (InformationClientValidateur) target;
		 
        // Check the fields of CustomerInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");
 
        if (!emailValidator.isValid(informationClient.)) {
            errors.rejectValue("email", "Pattern.customerForm.email");
        }
    }
 
}