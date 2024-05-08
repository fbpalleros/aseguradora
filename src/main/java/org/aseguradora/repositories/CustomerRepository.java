package org.aseguradora.repositories;


import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();

    Customer findOne(Long id);

    List<Policy> findPoliciesByIdCustomer(Long id);

    void save(Customer customer);


}
