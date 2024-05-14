package org.aseguradora.entity.dto;

public class AlmacenarDTO {

    public String nombre;
    public String modelo;
    public Integer anio;

    public AlmacenarDTO() {

    }

    public AlmacenarDTO(String nombre, String modelo, Integer anio) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}
