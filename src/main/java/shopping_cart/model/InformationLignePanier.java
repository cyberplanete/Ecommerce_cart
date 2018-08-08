package shopping_cart.model;

public class InformationLignePanier {

	private InformationProduit InfoProduit;
	private int quantité;
	
	public InformationLignePanier() {
		this.setQuantité(0);
	}

	public InformationProduit getInfoProduit() {
		return InfoProduit;
	}

	public void setInfoProduit(InformationProduit infoProduit) {
		InfoProduit = infoProduit;
	}

	public int getQuantité() {
		return quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}
	
	public double getMontant() {
		
		return this.InfoProduit.getPrix()*this.quantité;
	}
	
	

}
