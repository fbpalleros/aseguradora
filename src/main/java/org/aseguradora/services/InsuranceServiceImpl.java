package org.aseguradora.services;

import org.aseguradora.entity.Insurance;
import org.aseguradora.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService{


    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public Insurance findById(Long id) {
        return insuranceRepository.findById(id);
    }
}
