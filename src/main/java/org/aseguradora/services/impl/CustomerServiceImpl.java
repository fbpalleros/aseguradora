package org.aseguradora.services.impl;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Role;
import org.aseguradora.repositories.CustomerRepository;
import org.aseguradora.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findOne(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean customerHasRole(Long customerId, Long roleId) {
        Customer customer = customerRepository.findOne(customerId);
        if (customer != null) {
            for (Role role : customer.getRoles()) {
                if (role.getId().equals(roleId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Customer validateCredentials(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        String pass = customer.getPassword();
        if (pass.equals(password)) {
            return customer;
        }
        return null;
    }
}
