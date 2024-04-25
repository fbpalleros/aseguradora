package org.aseguradora.entity;

import javax.persistence.*;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customerId;

    private String description;






}
