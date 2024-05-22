package org.aseguradora.services.impl;

import org.aseguradora.entity.Insurance;
import org.aseguradora.repositories.InsuranceRepository;
import org.aseguradora.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public Insurance findById(Long id) {
        return insuranceRepository.findById(id);
    }
}
