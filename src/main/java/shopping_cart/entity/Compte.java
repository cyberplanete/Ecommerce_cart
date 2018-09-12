package shopping_cart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class Compte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7410214139500011331L;
	
	public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    private String nomUtilisateur;
    private String password;
    private boolean active;
    private String roleUtilisateur;
	
    
    @Id
    @Column(name = "Utilisateur_Nom", length = 20, nullable = false)
    public String getNomUtilisateur() {
		return nomUtilisateur;
	}
    
    @Column(name = "Password", length = 20, nullable = false)
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "Active", length = 1, nullable = false)
	public boolean estActif() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Column(name = "Role_Utilisateur", length = 20, nullable = false)
	public String getRoleUtilisateur() {
		return roleUtilisateur;
	}
	public void setRoleUtilisateur(String roleUtilisateur) {
		this.roleUtilisateur = roleUtilisateur;
	}
	@Override
	public String toString() {
		return "Compte [nomUtilisateur=" + nomUtilisateur + ", password=" + password + ", roleUtilisateur="
				+ roleUtilisateur + "]";
	}
    
    


}
