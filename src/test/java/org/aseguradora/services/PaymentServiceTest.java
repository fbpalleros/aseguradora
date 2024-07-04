package org.aseguradora.services;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Payment;
import org.aseguradora.repositories.PaymentRepository;
import org.aseguradora.services.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void init(){
        this.paymentRepository = mock(PaymentRepository.class);
        this.paymentService = new PaymentServiceImpl(paymentRepository);
    }

    @Test
    public void queSeObtengaUnaListaDeTodosLosPagos(){
        List<Payment> paymentsMock = new ArrayList<>();

        when(this.paymentRepository.findAll()).thenReturn(paymentsMock);
        List<Payment> payments = this.paymentService.findAll();

        assertThat(payments, equalToObject(paymentsMock));
    }

    @Test
    public void queSeGuardeElPago(){
        Payment payment = new Payment();
        payment.setId(1L);

        this.paymentService.save(payment);

        verify(this.paymentRepository).save(payment);
    }

    @Test
    public void queRetorneUnaListaConLosPagosDeUnCliente(){
        Customer customer = new Customer(1L, "Facundo", "facu@gmail.com", "12345");
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setCustomer(customer);

        Payment payment2 = new Payment();
        payment2.setId(2L);
        payment2.setCustomer(customer);

        List<Payment> paymentsMock = new ArrayList<>();
        paymentsMock.add(payment1);
        paymentsMock.add(payment2);

        when(this.paymentRepository.findByCustomerId(customer.getId())).thenReturn(paymentsMock);

        List<Payment> paymentList = this.paymentService.findByCustomerId(1L);

        assertThat(paymentList, equalToObject(paymentsMock));
    }





}
