package org.aseguradora.services;

import org.aseguradora.entity.Insurance;
import org.aseguradora.repositories.InsuranceRepository;
import org.aseguradora.services.impl.InsuranceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToObject;
import static org.mockito.Mockito.*;

public class InsuranceServiceTest {

    private InsuranceService insuranceService;
    private InsuranceRepository insuranceRepository;

    @BeforeEach
    public void init() {
        this.insuranceRepository = mock(InsuranceRepository.class);
        this.insuranceService = new InsuranceServiceImpl(insuranceRepository);
    }

    @Test
    public void queSeObtengaElTipoDeSeguroPorSuId() {
        Insurance insuranceMock = new Insurance();

        when(this.insuranceRepository.findById(1L)).thenReturn(insuranceMock);

        Insurance insurance = this.insuranceService.findById(1L);

        assertThat(insurance, equalToObject(insuranceMock));
        verify(insuranceRepository).findById(1L);
    }
}
