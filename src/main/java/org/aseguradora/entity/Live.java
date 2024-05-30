package org.aseguradora.entity;

import javax.persistence.*;

    @Entity
    @Table(name="vida")
    public class Live {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String oficio;

        private Integer anio;

        private Double precio;

        public Live(){

        }

        public Live(Long id, String ocupacion, Integer anio, Double precio) {
            this.id = id;
            this.oficio = ocupacion;
            this.anio = anio;
            this.precio = precio;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
    }
