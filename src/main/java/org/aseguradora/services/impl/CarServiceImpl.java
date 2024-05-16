package org.aseguradora.services.impl;

import org.aseguradora.repositories.CarRepository;
import org.aseguradora.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<String> findDistinctName() {
        return carRepository.findDistinctName();
    }

    @Override
    public List<String> findDistinctModelByName(String name) {
        return carRepository.findByModelWithParameter(name);
    }

    @Override
    public List<Integer> findDistinctByNameAndModel(String name , String model) {
        return carRepository.findDistinctByNameAndModel(name , model);
    }

}
