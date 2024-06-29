package org.aseguradora.services;

import org.aseguradora.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> findAll();

    void save(Payment payment);

    List<Payment> findByCustomerId(Long id);
}
