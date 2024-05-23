package org.aseguradora.services.impl;

import org.aseguradora.repositories.LiveRepository;
import org.aseguradora.services.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveServiceImpl  implements LiveService {

    private LiveRepository liveRepository;

    @Autowired
    public LiveServiceImpl(LiveRepository liveRepository) {
        this.liveRepository = liveRepository;
    }

    @Override
    public List<String> listarTodasLosOficios() {
        return liveRepository.listarTodasLosOficios();
    }

    @Override
    public List<Integer> buscarAnioPorOficio(String oficio) {
        return liveRepository.buscarAnioPorOficio(oficio);
    }

    @Override
    public Double buscarPrecioPorAnioYOficio(String oficio , Integer anio ) {
        return liveRepository.buscarPrecioPorAnioYOficio(oficio , anio);
    }

}
