package org.aseguradora.services.impl;

import org.aseguradora.repositories.LiveRepository;
import org.aseguradora.services.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveServiceImpl  implements LiveService {

    private final Double QUOTE_1 = 1.10 / 6;
    private final Double QUOTE_2 = 1.20 / 6;
    private final Double QUOTE_3 = 1.35 / 6;

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
