package org.aseguradora.entity;

import javax.persistence.*;

@Entity
@Table(name="ciudad")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String provincia;
    public String localidad;


    public City() {
    }

    public City(Long id, String provincia, String localidad) {
        this.id = id;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
