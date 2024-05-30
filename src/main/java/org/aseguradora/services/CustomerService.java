package org.aseguradora.services;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findOne(Long id);

    void save(Customer customer);

    List<Policy> findPoliciesByCustomerId(Long id);

    Customer findNameCustomer(String email, String password);

    void actualizar(Customer customer);
}
