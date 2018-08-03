package shopping_cart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.collections.functors.FalsePredicate;

@Entity
@Table(name = "Commandes", uniqueConstraints = {@UniqueConstraint(columnNames = "Commande_Num") })
public class Commande implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6874676974270242082L;
	
	private String id;
    private Date dateDeCommande;
    private int numéroDeCommande;
    private double montant;
 
    private String nomClient;
    private String addresseClient;
    private String emailClient;
    private String téléphoneClient;
	
    
    @Id
    @Column(name="id",length =50)
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "Commande_Date", nullable=false)
	public Date getDateDeCommande() {
		return dateDeCommande;
	}
	public void setDateDeCommande(Date dateDeCommande) {
		this.dateDeCommande = dateDeCommande;
	}
	@Column(name = "Commande_Num", nullable=false)
	public int getNuméroDeCommande() {
		return numéroDeCommande;
	}
	public void setNuméroDeCommande(int numéroDeCommande) {
		this.numéroDeCommande = numéroDeCommande;
	}
	@Column(name = "Montant", nullable=false)
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	@Column(name = "Client_Nom",length=255,nullable=false)
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	@Column(name = "Client_Addresse",length=255,nullable=false)
	public String getAddresseClient() {
		return addresseClient;
	}
	public void setAddresseClient(String addresseClient) {
		this.addresseClient = addresseClient;
	}
	@Column(name = "Client_Email",length=128,nullable=false)
	public String getEmailClient() {
		return emailClient;
	}
	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}
	@Column(name = "Client_Téléphone",length=128,nullable=false)
	public String getTéléphoneClient() {
		return téléphoneClient;
	}
	public void setTéléphoneClient(String téléphoneClient) {
		this.téléphoneClient = téléphoneClient;
	}
	

}
