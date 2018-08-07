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
	
	//ProduitID de commande detail à produitID de l'entity Produit
	//Relation plusieurs à un seul. Exemple: Dans le detail de ma commande, je peux avoir plusieurs écrans comportant le meme id
	//produit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUIT_ID",nullable=false,foreignKey=@ForeignKey(name="COMMANDE_DETAIL_PRODUIT_ID_FK"))
	public Produit getProduit() {
		return produitID;
	}
	
	public void setProduit(Produit produitID) {
		this.produitID=produitID;
	}
	
	@Column(name="Quantité",nullable=false)
	public int getQuantité() {
		return quantité;
	}
	
	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}
	
	@Column(name="Montant",nullable=false)
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Column(name="Montant", nullable=false)
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
}
