package org.aseguradora.services.impl;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.Role;
import org.aseguradora.repositories.CustomerRepository;
import org.aseguradora.repositories.PolicyRepository;
import org.aseguradora.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    PolicyRepository policyRepository;

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
    public List<Policy> findPoliciesByCustomerId(Long id) {
        return customerRepository.findPoliciesByIdCustomer(id);
    }

    @Override
    public Customer findNameCustomer(String email, String password){
        return customerRepository.findNameCustumer (email , password);
    }

    @Override
    public void actualizar(Customer customer) {
        customerRepository.actualizar(customer);
    }

    @Override
    public Policy pay(Policy policy) {
        policy.setCoverage(0.0);
        policyRepository.update(policy);
        return policy;
    }

    @Override
    public Policy findPolicyByIdCustomer(Long idCustomer, Long idPolicy) {
        return customerRepository.findPolicyByIdCustomer(idCustomer, idPolicy);
    }

    @Override
    public List<Policy> findPaidPoliciesByCustomerId(Long id) {
        List<Policy> allPolicies = customerRepository.findPoliciesByIdCustomer(id);
        Double paid = 0.0;
        List<Policy> paidPolicies = allPolicies.stream().filter(policy -> policy.getCoverage().equals(paid)).collect(Collectors.toList());
        return paidPolicies;
    }


}
