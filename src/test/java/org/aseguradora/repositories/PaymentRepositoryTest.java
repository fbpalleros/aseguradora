package org.aseguradora.repositories;


import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Payment;
import org.aseguradora.repositories.impl.CustomerRepositoryImpl;
import org.aseguradora.repositories.impl.PaymentRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

import javax.transaction.Transactional;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, CustomerRepositoryImpl.class, PaymentRepositoryImpl.class})
public class PaymentRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private PaymentRepository paymentRepository;
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init(){
        this.customerRepository = mock(CustomerRepository.class);
        this.paymentRepository = new PaymentRepositoryImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnPago(){
        dadoQueExisteUsuario();
        Customer customer = this.customerRepository.findOne(1L);

        Payment payment = new Payment();
        payment.setId(1L);
        payment.setCustomer(customer);
        payment.setAmount(2000.00);

        this.paymentRepository.save(payment);

        Payment paymentSearched = (Payment) this.sessionFactory.getCurrentSession().createQuery("SELECT p FROM Payment p WHERE id = 1").getSingleResult();

        assertThat(payment, equalToObject(paymentSearched));

    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtenganLosPagosDeUnUsuario(){
        dadoQueExisteUsuarioConPagos();

        List<Payment> payments = this.paymentRepository.findByCustomerId(1L);

        List<Payment> paymentList = (List<Payment>) this.sessionFactory.getCurrentSession().createQuery("SELECT p FROM Payment p WHERE p.customer.id = 1L").getResultList();

        assertThat(payments, equalToObject(paymentList));

    }

    private void dadoQueExisteUsuario() {
        Customer c1 = new Customer(1L, "Facundo", "facu@gmail.com", "12345");
        Mockito.when(this.customerRepository.findOne(1L)).thenReturn(c1);

        this.sessionFactory.getCurrentSession().save(c1);
    }

    private void dadoQueExisteUsuarioConPagos(){
        dadoQueExisteUsuario();
        Customer c1 = this.customerRepository.findOne(1L);

        Payment p1 = new Payment();
        p1.setId(1L);
        p1.setCustomer(c1);
        p1.setAmount(2000.00);

        Payment p2 = new Payment();
        p2.setId(2L);
        p2.setCustomer(c1);
        p2.setAmount(3000.00);

        this.sessionFactory.getCurrentSession().save(p1);
        this.sessionFactory.getCurrentSession().save(p2);
    }



}
