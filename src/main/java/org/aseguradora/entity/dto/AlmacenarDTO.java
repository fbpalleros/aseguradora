package org.aseguradora.entity.dto;

public class AlmacenarDTO {

    public String nombre;
    public String modelo;
    public Integer anio;
    public Double precio;
    public Double cotizacion;



    public AlmacenarDTO() {

    }



    public AlmacenarDTO(String nombre, String modelo, Integer anio , Double precio , Double cotizacion) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.anio = anio;
        this.precio=precio;
        this.cotizacion=cotizacion;

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

    public Double getPrecio() {return precio;}

    public void setPrecio(Double precio) {this.precio = precio;}

    public Double getCotizacion() {return cotizacion;}

    public void setCotizacion(Double cotizacion) {this.cotizacion = cotizacion;}
}
