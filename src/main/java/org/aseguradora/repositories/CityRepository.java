package org.aseguradora.repositories;

import java.util.List;

public interface CityRepository {

    List<String> listarTodasLasProvincias();

    List<String> buscarDependiendoLaProvincia(String provincia);
}
