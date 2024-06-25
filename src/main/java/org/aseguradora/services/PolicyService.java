package org.aseguradora.services;

import org.aseguradora.entity.Policy;

import java.util.List;

public interface PolicyService {

    List<Policy> findAll();

    Policy findById(Long id);

    List<Policy> findByCustomerId(Long id);

    void save (Policy policy);

    void delete(Policy policy);
}
