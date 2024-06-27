package org.aseguradora.services.impl;

import org.aseguradora.repositories.CarRepository;
import org.aseguradora.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private final Double QUOTE_1 = 1.30 / 6;
    private final Double QUOTE_2 = 1.15 / 6;
    private final Double QUOTE_3 = 1.20 / 6;


    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<String> findDistinctName() {
        return carRepository.findDistinctName();
    }

    @Override
    public List<String> findDistinctModelByName(String name) {
        return carRepository.findByModelWithParameter(name);
    }

    @Override
    public List<Integer> findDistinctByNameAndModel(String name, String model) {
        return carRepository.findDistinctByNameAndModel(name, model);
    }

    @Override
    public Double findPrice(String name, String model, Integer year) {
        return carRepository.findPrice(name, model, year);
    }

    @Override
    public Double applyQuote(Double price, Integer type) throws IllegalStateException {
        switch (type) {
            case 1:
                return (double) Math.round(price * QUOTE_1);
            case 2:
                return (double) Math.round(price * QUOTE_2);
            case 3:
                return (double) Math.round(price * QUOTE_3);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

}
