package org.aseguradora.services;

import org.aseguradora.repositories.CarRepository;
import org.aseguradora.services.impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalToObject;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    private CarService carService;
    private CarRepository carRepository;

    @BeforeEach
    public void init() {
        this.carRepository = mock(CarRepository.class);
        this.carService = new CarServiceImpl(carRepository);
    }

    @Test
    public void queSeObtenganSinRepetirLasMarcasDeLosAutomoviles() {
        List<String> namesMock = new ArrayList<>();
        namesMock.add("Ford");
        namesMock.add("Fiat");
        namesMock.add("Chevrolet");
        namesMock.add("Honda");

        when(this.carRepository.findDistinctName()).thenReturn(namesMock);

        List<String> names = this.carService.findDistinctName();

        assertThat(names, equalToObject(namesMock));
        assertThat(names.size(), equalTo(4));
        verify(carRepository).findDistinctName();
    }

    @Test
    public void queSeObtenganLosModelosSegunSuMarca() {
        List<String> modelsMock = new ArrayList<>();
        modelsMock.add("Palio");
        modelsMock.add("Mobi");
        modelsMock.add("Strada");
        modelsMock.add("Fiorino");

        when(this.carRepository.findByModelWithParameter("Fiat")).thenReturn(modelsMock);

        List<String> models = this.carService.findDistinctModelByName("Fiat");

        assertThat(models, equalToObject(modelsMock));
        verify(carRepository).findByModelWithParameter("Fiat");
    }

    @Test
    public void queSeObtenganLosAniosDeLosAutomoviles() {
        List<Integer> yearsMock = new ArrayList<>();
        when(this.carRepository.findDistinctByNameAndModel("Fiat", "Palio")).thenReturn(yearsMock);

        List<Integer> years = this.carService.findDistinctByNameAndModel("Fiat", "Palio");

        assertThat(years, equalToObject(yearsMock));
        verify(carRepository).findDistinctByNameAndModel("Fiat", "Palio");
    }

    @Test
    public void queSeObtengaElPrecioDelVehiculo() {
        Double priceMock = 120000.00;

        when(this.carRepository.findPrice("Fiat", "Palio", 2001)).thenReturn(priceMock);

        Double price = this.carService.findPrice("Fiat", "Palio", 2001);

        assertThat(price, equalToObject(priceMock));
        verify(carRepository).findPrice("Fiat", "Palio", 2001);
    }

    @Test
    public void queSeApliqueLaCuotaCorrespondienteSegunElTipoDeCobertura1() {
        Double priceMock = 120000.00;
        Integer type = 1;

        Double QUOTE_1 = 1.30 / 6;

        Double quote = this.carService.applyQuote(priceMock, type);

        assertThat(quote, equalTo(priceMock * QUOTE_1));
    }

    @Test
    public void queSeApliqueLaCuotaCorrespondienteSegunElTipoDeCobertura2() {
        Double priceMock = 120000.00;
        Integer type = 2;

        Double QUOTE_2 = 1.15 / 6;

        Double quote = this.carService.applyQuote(priceMock, type);

        assertThat(quote, equalTo(priceMock * QUOTE_2));
    }



}
