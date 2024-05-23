package org.aseguradora.repositories;

import java.util.List;

public interface LiveRepository {

    List<String> listarTodasLosOficios();

    List<Integer> buscarAnioPorOficio(String oficio);

    Double buscarPrecioPorAnioYOficio(String oficio , Integer anio );
}
