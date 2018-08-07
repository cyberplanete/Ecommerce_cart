package shopping_cart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	
	
}
