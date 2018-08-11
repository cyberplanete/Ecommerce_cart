package shopping_cart.model;

public class InformationCommande {
	
	private String id;
	
	private String codeProduit;
	private String nomPoduit;
	
	private int quantité;
	private double prix;
	
	private double montant ;

	
	public InformationCommande() {
		
	}
	
	
	//Utilisation hibernate query
	public InformationCommande(String id, String codeProduit, int quantité, double prix, double montant) {
		this.id=id;
		this.codeProduit = codeProduit;
		this.quantité = quantité;
		this.prix = prix;
		this.montant = montant;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCodeProduit() {
		return codeProduit;
	}


	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}


	public String getNomPoduit() {
		return nomPoduit;
	}


	public void setNomPoduit(String nomPoduit) {
		this.nomPoduit = nomPoduit;
	}


	public int getQuantité() {
		return quantité;
	}


	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
}
