package org.aseguradora.services;

import org.aseguradora.repositories.LiveRepository;
import org.aseguradora.services.impl.LiveServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class LiveServiceTest {

    private LiveService liveService;
    private LiveRepository liveRepository;

    @BeforeEach
    public void init(){
        this.liveRepository = mock(LiveRepository.class);
        this.liveService = new LiveServiceImpl(liveRepository);
    }

    @Test
    public void queSeObtengaUnaListaDeTodosLosOficios(){
        List<String> oficiosMock = new ArrayList<>();

        when(this.liveRepository.listarTodasLosOficios()).thenReturn(oficiosMock);
        List<String> oficios = this.liveService.listarTodasLosOficios();

        assertThat(oficios, equalToObject(oficiosMock));
        verify(liveRepository).listarTodasLosOficios();
    }

    @Test
    public void queSeObtegaUnaListaDeLasEdadesSegunOficio(){
        List<Integer> listMock = new ArrayList<>();

        when(this.liveRepository.buscarAnioPorOficio("Camionero")).thenReturn(listMock);
        List<Integer> list = this.liveService.buscarAnioPorOficio("Camionero");

        assertThat(list, equalToObject(listMock));
        verify(liveRepository).buscarAnioPorOficio("Camionero");
    }

    @Test
    public void queSeObtengaElPrecioSegunAnioYOficio(){
        Double priceMock = 200.0;

        when(this.liveRepository.buscarPrecioPorAnioYOficio("Camionero", 1994)).thenReturn(priceMock);

        Double price = this.liveService.buscarPrecioPorAnioYOficio("Camionero", 1994);

        assertThat(price, equalToObject(priceMock));
        verify(liveRepository).buscarPrecioPorAnioYOficio("Camionero", 1994);

    }
}
