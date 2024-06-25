package org.aseguradora.repositories;

import org.aseguradora.entity.Policy;

import java.util.List;

public interface PolicyRepository {

    List<Policy> findAll();
    //Probablemente debo realizar la consulta desde Costumer
    List<Policy> findByCustomerId(Long id); //by Customer

    Policy findById(Long id);

    void save(Policy policy);

    void update(Policy policy);

    void delete(Policy policy);
}
