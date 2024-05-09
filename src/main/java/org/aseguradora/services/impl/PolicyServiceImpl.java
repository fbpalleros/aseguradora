package org.aseguradora.services.impl;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.PolicyDto;
import org.aseguradora.repositories.PolicyRepository;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    PolicyRepository policyRepository;


    @Override
    public List<Policy> findAll() {
        return policyRepository.findAll();
    }

    @Override
    public List<PolicyDto> findAllDto() {
        return policyRepository.findAllDto();
    }

    @Override
    public Object findInsuranceType() {
        return policyRepository.findInsuranceType();
    }

    @Override
    public Policy findById(Long id) {
        return policyRepository.findById(id);
    }

    @Override
    public List<Policy> findByIdObjeto( Customer customer) {
        return policyRepository.findByIdObjeto(customer);
    }

    @Override
    public void save(Policy policy) {
        policyRepository.save(policy);
    }


}
