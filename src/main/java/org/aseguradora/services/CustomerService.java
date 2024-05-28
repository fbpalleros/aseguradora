package org.aseguradora.services;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Customer findOne(Long id);

    void save(Customer customer);

    List<Policy> findPoliciesByCustomerId(Long id);

    boolean customerHasRole(Long customerId, Long roleId);

    Customer validateCredentials(String email, String password);

    Customer findNameCustumer(String email, String password);

    void actualizar(Customer custumer);
}
