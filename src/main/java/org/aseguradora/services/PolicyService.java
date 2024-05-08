package org.aseguradora.services;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.PolicyDto;

import java.util.List;

public interface PolicyService {

    List<Policy> findAll();

    List<PolicyDto> findAllDto();

    Object findInsuranceType();

    List<Policy> findById(Long id);

    List<Policy> findByIdObjeto(Customer customer);

    void save (Policy policy);
}
