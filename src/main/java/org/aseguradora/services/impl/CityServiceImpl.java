package org.aseguradora.services.impl;

import org.aseguradora.repositories.CarRepository;
import org.aseguradora.repositories.CityRepository;
import org.aseguradora.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final Double QUOTE_1 = 1.30 / 6;
    private final Double QUOTE_2 = 1.50 / 6;

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    @Override
    public List<String> listarTodasLasProvincias() {
        return cityRepository.listarTodasLasProvincias();
    }

    @Override
    public List<String> buscarDependiendoLaProvincia(String provincia) {
        return cityRepository.buscarDependiendoLaProvincia(provincia);
    }

    @Override
    public Double applyQuote(Double metros, Integer type) throws IllegalStateException {
        switch (type) {
            case 1:
                return (double) Math.round(metros * QUOTE_1);
            case 2:
                return (double) Math.round(metros * QUOTE_2);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

}
