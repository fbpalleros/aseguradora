package org.aseguradora.entity;

import javax.persistence.*;

@Entity
@Table(name="auto")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String model;

    private Integer anio;

    private Double precio;


    public Car(){

    }
    public Car ( Long id, String name , String model , Integer anio , Double precio){
        this.id = id;
        this.name = name;
        this.model = model;
        this.anio = anio;
        this.precio= precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
