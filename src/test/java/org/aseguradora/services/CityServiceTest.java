package org.aseguradora.services;

import org.aseguradora.repositories.CityRepository;
import org.aseguradora.services.impl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CityServiceTest {

    private CityService cityService;
    private CityRepository cityRepository;

    @BeforeEach
    public void init(){
        this.cityRepository = mock(CityRepository.class);
        this.cityService = new CityServiceImpl(cityRepository);
    }

    @Test
    public void queSeObtengaUnaListaConTodasLasProvincias(){
        List<String> provinciasMock = new ArrayList<>();

        when(this.cityRepository.listarTodasLasProvincias()).thenReturn(provinciasMock);
        List<String> provincias = this.cityService.listarTodasLasProvincias();

        assertThat(provincias, equalTo(provinciasMock));
        verify(cityRepository).listarTodasLasProvincias();
    }

    @Test
    public void queSeObtenganLasLocalidadesDeLaProvincia(){
        List<String> localidadesMock = new ArrayList<>();

        when(this.cityRepository.buscarDependiendoLaProvincia("Buenos Aires")).thenReturn(localidadesMock);

        List<String> localidades = this.cityService.buscarDependiendoLaProvincia("Buenos Aires");

        assertThat(localidades, equalTo(localidadesMock));
        verify(cityRepository).buscarDependiendoLaProvincia("Buenos Aires");
    }
}
