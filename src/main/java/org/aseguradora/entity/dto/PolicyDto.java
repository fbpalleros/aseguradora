package org.aseguradora.entity.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PolicyDto {

    private String insuranceType;

    @DateTimeFormat(pattern = "yy-MM-dd")
    private Date expiration;

    private Integer coverage;

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Integer getCoverage() {
        return coverage;
    }

    public void setCoverage(Integer coverage) {
        this.coverage = coverage;
    }
}
