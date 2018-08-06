package shopping_cart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name="Commande_Details")
public class CommandeDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7459280817859329515L;
	
	private String id;
	private Commande commandeID;
	
	private Produit produitID;
	private int quantité;
	private double prix;
	private double montant;

	@Column(name="id", length=50)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COMMANDE_ID",nullable=false, foreignKey=@ForeignKey(name="COMMANDE_DETAIL_A_COMMANDE_ID_FK"))
	public Commande getCommandeID() {
		return commandeID;
	}
	
	public void setCommande(Commande commandeID) {
		this.commandeID=commandeID;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUIT_ID",nullable=false,foreignKey=@ForeignKey(name="COMMANDE_DETAIL_PRODUIT_ID_FK"))
	public Produit getProduit() {
		return produitID;
	}
	
	public void setProduit(Produit produitID) {
		this.produitID=produitID;
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
