package org.aseguradora.entity;

import javax.persistence.*;
import java.util.Date;

//@Entity
//public class Payment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne //coments
//    private Customer customerId;
//
//    @OneToOne //es uno a uno? única póliza?
//    private Insurance insuranceId;
//
//    private Long amount;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "create_at")
//    private Date paymentDate;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Customer getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Customer customerId) {
//        this.customerId = customerId;
//    }
//
//    public Insurance getInsuranceId() {
//        return insuranceId;
//    }
//
//    public void setInsuranceId(Insurance insuranceId) {
//        this.insuranceId = insuranceId;
//    }
//
//    public Long getAmount() {
//        return amount;
//    }
//
//    public void setAmount(Long amount) {
//        this.amount = amount;
//    }
//
//    public Date getPaymentDate() {
//        return paymentDate;
//    }
//
//    public void setPaymentDate(Date paymentDate) {
//        this.paymentDate = paymentDate;
//    }
//}
