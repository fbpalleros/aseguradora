//package org.aseguradora.entity;
//
//import javax.persistence.*;
//
//@Entity
//public class Claim {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    private Customer customerId;
//
//    private String description;
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
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}
