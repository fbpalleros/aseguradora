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

    private Integer year;

    //private Double price;

    public Car(){

    }
    public Car ( Long id, String name , String model , Integer year){
        this.id = id;
        this.name = name;
        this.model = model;
        this.year = year;
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

    public Integer getYear() {
        return year;
    }

    public void setPrice(Integer price) {
        this.year = year;
    }
}
