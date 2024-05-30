package org.aseguradora.services;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
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

    @Test
    public void queSeObtenganLasPolizasDeUnCliente(){
        Customer customerMock = new Customer();
        customerMock.setId(1L);

        List<Policy> policiesMock = new ArrayList<>();

        when(this.customerRepository.findPoliciesByIdCustomer(customerMock.getId())).thenReturn(policiesMock);

        List<Policy> policies = this.customerService.findPoliciesByCustomerId(1L);

        assertThat(policies, equalToObject(policiesMock));
        verify(customerRepository).findPoliciesByIdCustomer(1L);
    }

    @Test
    public void queSeObtengaElClientePorSuEmailYPassword(){
        Customer customerMock = new Customer();
        customerMock.setId(1L);
        customerMock.setEmail("john@gmail.com");
        customerMock.setPassword("123456");

        when(this.customerRepository.findNameCustumer(customerMock.getEmail(), customerMock.getPassword())).thenReturn(customerMock);
        Customer customer = this.customerService.findNameCustomer("john@gmail.com", "123456");

        assertThat(customer, equalToObject(customerMock));
        verify(customerRepository).findNameCustumer(customerMock.getEmail(), customerMock.getPassword());
    }

    @Test
    public void queSeActualicenLosDatosDelCliente(){
        Customer customerMock = new Customer();
        this.customerService.actualizar(customerMock);
        verify(customerRepository).actualizar(customerMock);
    }
}
