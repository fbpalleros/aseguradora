package org.aseguradora.entity.dto;

public class AlmacenarCasaDTO {

    public String provincia;
    public String localidad;
    public Double metros;
    public Double cotizacion;
    private Integer type;

    public AlmacenarCasaDTO() {
    }

    public AlmacenarCasaDTO(String provincia, String localidad, Double metros, Double cotizacion) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.metros = metros;
        this.cotizacion = cotizacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Double getMetros() {
        return metros;
    }

    public void setMetros(Double metros) {
        this.metros = metros;
    }

    public Double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
