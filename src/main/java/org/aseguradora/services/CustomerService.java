package org.aseguradora.services;

import org.aseguradora.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Customer findOne(Long id);

    void save(Customer customer);

    boolean customerHasRole(Long customerId, Long roleId);

    Customer validateCredentials(String email, String password);

    Customer findNameCustumer(String email, String password);
}
