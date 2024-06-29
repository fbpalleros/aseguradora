package org.aseguradora.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String message;

    public Contact ( String nombre, String email , String telefono , String asunto , String mensaje){
        this.name = nombre;
        this.email = email;
        this.phone = telefono;
        this.subject = asunto;
        this.message= mensaje;
    }
    
	public String getNombre() {
		return name;
	}
	public void setNombre(String nombre) {
		this.name = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return phone;
	}
	public void setTelefono(String telefono) {
		this.phone = telefono;
	}
	public String getAsunto() {
		return subject;
	}
	public void setAsunto(String asunto) {
		this.subject = asunto;
	}
	public String getMensaje() {
		return message;
	}
	public void setMensaje(String mensaje) {
		this.message = mensaje;
	}
}
    