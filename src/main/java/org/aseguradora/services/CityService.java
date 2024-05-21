package org.aseguradora.services;

import java.util.List;

public interface CityService {

    List<String> listarTodasLasProvincias();

    List<String> buscarDependiendoLaProvincia(String provincia);
}
