package shopping_cart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Produit")
public class Produit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8126139673014640390L;
	
	private String code;
	private String nom;
	private double prix;
	private byte[] image;
	
	//Pour le tri
	private Date CreateDate ;
	
	public Produit() {}

	@Id
	@Column(name="Code",length=20,nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="Nom",length=255,nullable=false)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	@Column(name="Prix", nullable=false)
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	//BLOB – Binary Large Object is for storing binary data like image, audio, or video
	@Lob
	@Column(name="Image",length=Integer.MAX_VALUE,nullable=true)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
//	Cette annotation doit être spécifiée pour les champs persistants ou les propriétés de type java.util.Date 
//	et java.util.Calendar. Il ne peut être spécifié que pour les champs ou les propriétés de ces types.
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Create_Date",nullable=false)
	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	
	
}
