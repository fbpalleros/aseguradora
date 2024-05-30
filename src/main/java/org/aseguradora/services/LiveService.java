package org.aseguradora.services;

import java.util.List;

public interface LiveService {

    List<String> listarTodasLosOficios();

    List<Integer> buscarAnioPorOficio(String oficio);

    Double buscarPrecioPorAnioYOficio(String oficio , Integer anio );
}
