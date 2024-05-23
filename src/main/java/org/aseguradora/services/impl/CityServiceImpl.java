package org.aseguradora.services.impl;

import org.aseguradora.repositories.CityRepository;
import org.aseguradora.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
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

}
