package org.aseguradora.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Denuncia {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "policy_id")
    private Policy policy;
	
    @Column(name = "accidentDate", nullable = false )
    private String accidentDate;

    @Column(name = "accidentHour", nullable = false )
    private String accidentHour;
    
    @Column(name = "description", nullable = false )
    private String description;
    
    // Campos opcionales
    
    @Column(name = "adress", nullable = true )
    private String adress;
    
    @Column(name = "adressNumber", nullable = true )
    private String adressNumber;
    
    @Column(name = "postalCode", nullable = true )
    private String postalCode;
    
    @Column(name = "damageType", nullable = true )
    private String damageType;
    
    @Column(name = "victimFirstName", nullable = true )
    private String victimFirstName;
    
    @Column(name = "victimLastName", nullable = true )
    private String victimLastName;
    
    @Column(name = "victimPhone", nullable = true )
    private String victimPhone;
    
    @Column(name = "victimDocument", nullable = true )
    private Integer victimDocument;
    
    @Column(name = "victimEmail", nullable = true )
    private String victimEmail;
    
    @Column(name = "victimPatent", nullable = true )
    private String victimPatent;
    
    @Column(name = "descriptionVictimDamage", nullable = true )
    private String descriptionVictimDamage;
    
    // GETTERS && SETTERS
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getAccidentHour() {
		return accidentHour;
	}
	public void setAccidentHour(String accidentHour) {
		this.accidentHour = accidentHour;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getAdressNumber() {
		return adressNumber;
	}
	public void setAdressNumber(String adressNumber) {
		this.adressNumber = adressNumber;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDamageType() {
		return damageType;
	}
	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}
	public String getVictimFirstName() {
		return victimFirstName;
	}
	public void setVictimFirstName(String victimFirstName) {
		this.victimFirstName = victimFirstName;
	}
	public String getVictimLastName() {
		return victimLastName;
	}
	public void setVictimLastName(String victimLastName) {
		this.victimLastName = victimLastName;
	}
	public String getVictimPhone() {
		return victimPhone;
	}
	public void setVictimPhone(String victimPhone) {
		this.victimPhone = victimPhone;
	}
	public Integer getVictimDocument() {
		return victimDocument;
	}
	public void setVictimDocument(Integer victimDocument) {
		this.victimDocument = victimDocument;
	}
	public String getVictimEmail() {
		return victimEmail;
	}
	public void setVictimEmail(String victimEmail) {
		this.victimEmail = victimEmail;
	}
	public String getVictimPatent() {
		return victimPatent;
	}
	public void setVictimPatent(String victimPatent) {
		this.victimPatent = victimPatent;
	}
	public String getDescriptionVictimDamage() {
		return descriptionVictimDamage;
	}
	public void setDescriptionVictimDamage(String descriptionVictimDamage) {
		this.descriptionVictimDamage = descriptionVictimDamage;
	}
}