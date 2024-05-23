package org.aseguradora.services.impl;

import org.aseguradora.repositories.LiveRepository;
import org.aseguradora.services.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveServiceImpl  implements LiveService {

    @Autowired
    private LiveRepository liveService;

    @Override
    public List<String> listarTodasLosOficios() {
        return liveService.listarTodasLosOficios();
    }

    @Override
    public List<Integer> buscarAnioPorOficio(String oficio) {
        return liveService.buscarAnioPorOficio(oficio);
    }

    @Override
    public Double buscarPrecioPorAnioYOficio(String oficio , Integer anio ) {
        return liveService.buscarPrecioPorAnioYOficio(oficio , anio);
    }

}
