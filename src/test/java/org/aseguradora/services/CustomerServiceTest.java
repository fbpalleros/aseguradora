package org.aseguradora.services;

import org.aseguradora.entity.Customer;
import org.aseguradora.repositories.CustomerRepository;
import org.aseguradora.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init(){
        this.customerRepository = mock(CustomerRepository.class);
        this.customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void queSeObtengaUnaListaConTodosLosClientes(){
        List<Customer> customersMock = new ArrayList<>();

        when(this.customerRepository.findAll()).thenReturn(customersMock);
        List<Customer> customers = this.customerService.findAll();

        assertThat(customers, equalTo(customersMock));
        verify(customerRepository).findAll();
    }

    @Test
    public void queSeObtengaUnClientePorSuId(){
        Customer customerMock = new Customer();
        customerMock.setId(1L);

        when(this.customerRepository.findOne(1L)).thenReturn(customerMock);
        Customer customer = this.customerService.findOne(1L);

        assertThat(customer, equalToObject(customerMock));
        assertThat(customer.getId(), equalTo(customerMock.getId()) );
        verify(customerRepository).findOne(1L);
    }
}
