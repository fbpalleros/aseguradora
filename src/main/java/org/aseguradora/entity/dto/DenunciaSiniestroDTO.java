package org.aseguradora.entity.dto;

import java.util.Date;

import org.aseguradora.entity.Policy;

public class DenunciaSiniestroDTO {
	
	private Policy policy;
    private String accidentDate;
    private String accidentHour;
    private String adress;
    private String adressNumber;
    private String postalCode;
    private String description;
    
    private String damageType;
    private String victimFirstName;
    private String victimLastName;
    private String victimPhone;
    private Integer victimDocument;
    private String victimEmail;
    private String victimPatent;
    private String descriptionVictimDamage;
	


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
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
}