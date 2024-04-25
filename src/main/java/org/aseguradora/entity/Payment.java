package org.aseguradora.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //coments
    private Customer customerId;

    @OneToOne //es uno a uno? única póliza?
    private Insurance insuranceId;

    private Long amount;

    private Date paymentDate;

}
