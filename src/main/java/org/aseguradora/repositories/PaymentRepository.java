package org.aseguradora.repositories;

import org.aseguradora.entity.Payment;

import java.util.List;

public interface PaymentRepository {

    List<Payment> findAll();

    void save(Payment payment);

    List<Payment> findByCustomerId(Long customerId);
}
