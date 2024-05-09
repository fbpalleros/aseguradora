package org.aseguradora.services.impl;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.PolicyDto;
import org.aseguradora.repositories.PolicyRepository;
import org.aseguradora.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public List<Policy> findById(Long id) {
        return policyRepository.findById(id);
    }

    @Override
    public void save(Policy policy) {
        policyRepository.save(policy);
    }


}
