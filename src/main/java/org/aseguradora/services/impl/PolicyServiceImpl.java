package org.aseguradora.services.impl;

import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.PolicyRepository;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    PolicyRepository policyRepository;

    @Autowired
    public PolicyServiceImpl(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<Policy> findAll() {
        return policyRepository.findAll();
    }

    @Override
    public Policy findById(Long id) {
        return policyRepository.findById(id);
    }

    @Override
    public List<Policy> findByCustomerId(Long id) {
        return policyRepository.findByCustomerId(id);
    }

    @Override
    public void save(Policy policy) {
        policyRepository.save(policy);
    }

    @Override
    public void delete(Policy policy) {
        policyRepository.delete(policy);
    }


}
