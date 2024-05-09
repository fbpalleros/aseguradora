package org.aseguradora.repositories;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.PolicyDto;

import java.util.List;

public interface PolicyRepository {

    List<Policy> findAll();
    
    //Probablemente debo realizar la consulta desde Costumer
    List<Policy> findById(Long id); //by Customer //by Customer

    void save(Policy policy);
}
