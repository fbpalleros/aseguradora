package org.aseguradora.services.impl;

import org.aseguradora.entity.Payment;
import org.aseguradora.repositories.PaymentRepository;
import org.aseguradora.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public List<Payment> findByCustomerId(Long id) {
        return paymentRepository.findByCustomerId(id);
    }
}
