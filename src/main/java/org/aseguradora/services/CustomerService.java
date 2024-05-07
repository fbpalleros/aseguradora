package org.aseguradora.services;

import org.aseguradora.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findOne(Long id);
}
