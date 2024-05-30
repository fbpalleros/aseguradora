package org.aseguradora.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Denuncia;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.CustomerRepository;
import org.aseguradora.repositories.DenunciaRepository;
import org.aseguradora.services.impl.CustomerServiceImpl;
import org.aseguradora.services.impl.DenunciaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DenunciaServiceTest {

    private DenunciaService denunciaService;
    private DenunciaRepository denunciaRepository;
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init(){
        this.denunciaRepository = mock(DenunciaRepository.class);
        this.denunciaService = new DenunciaServiceImpl(denunciaRepository);
    }

    @Test
    public void queSeObtengaTodasLasDenuncias(){
        List<Denuncia> denunciasMock = new ArrayList<>();

        when(this.denunciaRepository.findAll()).thenReturn(denunciasMock);
        List<Denuncia> denuncias = this.denunciaService.findAll();

        assertThat(denuncias, equalTo(denunciasMock));
        
        verify(denunciaRepository).findAll();
    }

    @Test
    public void queSeObtengaTodasLasDenunciaPorCustomer(){
    	Customer customerMock = new Customer();
        customerMock.setId(1L);
        
        Policy polizaMock = new Policy();
        polizaMock.setCustomer(customerMock);
    	
    	Denuncia unaDenuncia = new Denuncia();
    	unaDenuncia.setPolicy(polizaMock);
    	
    	Denuncia unaDenuncia2 = new Denuncia();
    	unaDenuncia2.setPolicy(polizaMock);
    	
    	List<Denuncia> denunciasMock = new ArrayList<Denuncia>();
    	denunciasMock.add(unaDenuncia);
    	denunciasMock.add(unaDenuncia);

        when(this.denunciaRepository.findAllByCustomerId(1L)).thenReturn(denunciasMock);
        List<Denuncia> denuncias = this.denunciaService.findAllByCustomerId(customerMock.getId());

        assertThat(denuncias, equalToObject(denunciasMock));
        verify(denunciaRepository).findAllByCustomerId(customerMock.getId());
    }
}
