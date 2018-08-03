package shopping_cart.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Commande_Details")
public class CommandeDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7459280817859329515L;
	
	private String id;
	private Commande commande;
	
	private Produit produit;
	private int quantité;
	private double prix;
	private double montant;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
}
