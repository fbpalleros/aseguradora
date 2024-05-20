package org.aseguradora.services;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.PolicyRepository;
import org.aseguradora.services.impl.PolicyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PolicyServiceTest {

    private PolicyService policyService;

    private PolicyRepository policyRepository;

    @BeforeEach
    public void init(){
        this.policyRepository = mock(PolicyRepository.class);
        this.policyService = new PolicyServiceImpl(policyRepository);
    }

    @Test
    public void queSePuedanObtenerTodasLasPolizas() {
        List<Policy> polizasMock = new ArrayList<>();
        polizasMock.add(new Policy());
        polizasMock.add(new Policy());
        polizasMock.add(new Policy());

        when(this.policyRepository.findAll()).thenReturn(polizasMock);

        List<Policy> policies = policyService.findAll();

        assertThat(policies.size(), equalTo(3));
    }

    @Test
    public void queSePuedaObtenerUnaPolizaPorSuId() {
        Policy polizaMock = new Policy();
        polizaMock.setId(2L);

        when(this.policyRepository.findById(2L)).thenReturn(polizaMock);

        Policy policy = policyService.findById(2L);

        assertThat(policy.getId(), equalTo(2L));
    }


    @Test
    public void queSePuedanObtenerLasPolizasDeUnClientePorSuId(){
        Customer customerMock = new Customer(1L, "facu", "facu@gmail.com");

        List<Policy> polizasMock = new ArrayList<>();
        polizasMock.add(new Policy(1L, 120));
        polizasMock.add(new Policy(2L, 200));
        polizasMock.add(new Policy(3L, 400));



    }



}
