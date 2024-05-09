package org.aseguradora.entity;

import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.Date;

@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Integer coverage;

    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date expiration;

    public Policy() {
    }

    public Policy(Long id, Integer coverage) {
        this.id = id;
        this.coverage = coverage;
    }

    public Policy(Integer coverage) {
        this.coverage = coverage;
    }

    public Policy(Long id, Insurance insurance, Customer customer, Integer coverage) {
        this.id = id;
        this.insurance = insurance;
        this.customer = customer;
        this.coverage = coverage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getCoverage() {
        return coverage;
    }

    public void setCoverage(Integer coverage) {
        this.coverage = coverage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", insuranceId=" + insurance +
                ", customerId=" + customer +
                ", coverage=" + coverage +
                ", startDate=" + startDate +
                ", expiration=" + expiration +
                '}';
    }
}
