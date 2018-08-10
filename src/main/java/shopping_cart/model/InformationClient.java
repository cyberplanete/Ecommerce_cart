package shopping_cart.model;

public class InformationClient {
	
	private String nom;
	private String prenom;
	private String adresse_rue;
	private String adresse_codePostal;
	private String adresse_ville;
	private String telephone;
	private String Email;
	

private boolean valide;

	public boolean isValide() {
		// TODO Stub de la méthode généré automatiquement
		return valide;
	}
	
	public void setValide(boolean valide) {
		this.valide = valide;
	}




	public String getNom() {
		return nom;
	}




	public void setNom(String nom) {
		this.nom = nom;
	}




	public String getPrenom() {
		return prenom;
	}




	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	public String getAdresse_rue() {
		return adresse_rue;
	}




	public void setAdresse_rue(String adresse_rue) {
		this.adresse_rue = adresse_rue;
	}




	public String getAdresse_codePostal() {
		return adresse_codePostal;
	}




	public void setAdresse_codePostal(String adresse_codePostal) {
		this.adresse_codePostal = adresse_codePostal;
	}




	public String getAdresse_ville() {
		return adresse_ville;
	}




	public void setAdresse_ville(String adresse_ville) {
		this.adresse_ville = adresse_ville;
	}




	public String getTelephone() {
		return telephone;
	}




	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}




	public String getEmail() {
		return Email;
	}




	public void setEmail(String email) {
		Email = email;
	}

	
	
	
	
}
