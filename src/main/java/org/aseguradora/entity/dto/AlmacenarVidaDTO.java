package org.aseguradora.entity.dto;

public class AlmacenarVidaDTO {

    public String oficio;
    public Integer anio;
    public Double precio;
    public Double cotizacion;

    public AlmacenarVidaDTO() {
    }

    public AlmacenarVidaDTO(Double cotizacion, Double precio, Integer anio, String oficio) {
        this.cotizacion = cotizacion;
        this.precio = precio;
        this.anio = anio;
        this.oficio = oficio;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
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

    public Double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }
}
