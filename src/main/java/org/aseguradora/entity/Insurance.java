package org.aseguradora.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insurance_type")
    @Enumerated(EnumType.STRING)
    private InsuranceType insuranceType;

    public Insurance(Long id, String name, InsuranceType insuranceType, Integer coverage) {
        this.id = id;
        this.insuranceType = insuranceType;
    }

    public Insurance() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    @Override
    public String toString() {
        return this.insuranceType.toString();
    }
}
