package org.aseguradora.services;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.PolicyDto;

import java.util.List;

public interface PolicyService {

    List<Policy> findAll();

    List<Policy> findById(Long id);

    void save (Policy policy);
}
