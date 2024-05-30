package org.aseguradora.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.impl.CustomerRepositoryImpl;
import org.aseguradora.repositories.impl.PolicyRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, CustomerRepositoryImpl.class})
class CustomerRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private CustomerRepository customerRepository;

    private PolicyRepository policyRepository;

    @BeforeEach
    public void init() {
        this.policyRepository = new PolicyRepositoryImpl(this.sessionFactory);
        this.customerRepository = new CustomerRepositoryImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtenganTodosLosClientes() {
        List<Customer> customersMock = getCustomers();
        List<Customer> customers = this.customerRepository.findAll();

        assertThat(customers, equalTo(customersMock));
        assertThat(customers.size(), equalTo(4));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtengaUnClientePorSuId() {
        Customer customerMock = getCustomer();
        Customer customer = this.customerRepository.findOne(1L);

        assertThat(customer, equalTo(customerMock));
        assertThat(customer.getId(), equalTo(1L));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtenganLasPolizasDeUnCliente() {
        List<Policy> policiesMock = getPoliciesByCustomerId();
        List<Policy> policies = this.customerRepository.findPoliciesByIdCustomer(1L);

        assertThat(policies, equalTo(policiesMock));
        assertThat(policies.size(), equalTo(1));
    }

    private List<Customer> getCustomers() {
        List<Customer> customersMock = new ArrayList<>();
        customersMock.add(new Customer(1L, "Jorge", "jorge@gmail.com", "12345"));
        customersMock.add(new Customer(2L, "Nahuel", "nahuel@gmail.com", "12345"));
        customersMock.add(new Customer(3L, "Marcelo", "marcelo@gmail.com", "12345"));
        customersMock.add(new Customer(4L, "Martin", "martin@gmail.com", "12345"));

        Policy p1 = new Policy();
        p1.setId(1L);
        p1.setCustomer(customersMock.get(0));

        this.sessionFactory.getCurrentSession().save(customersMock.get(0));
        this.sessionFactory.getCurrentSession().save(customersMock.get(1));
        this.sessionFactory.getCurrentSession().save(customersMock.get(2));
        this.sessionFactory.getCurrentSession().save(customersMock.get(3));
        this.sessionFactory.getCurrentSession().save(p1);

        return this.sessionFactory.getCurrentSession().createQuery("SELECT c FROM Customer c")
                .getResultList();
    }

    private Customer getCustomer() {
        getCustomers();
        return (Customer) this.sessionFactory.getCurrentSession().createQuery("SELECT c FROM Customer c WHERE c.id=?1")
                .setParameter(1, 1L)
                .getSingleResult();
    }

    private List<Policy> getPoliciesByCustomerId() {
        getCustomers();
        return this.sessionFactory.getCurrentSession().createQuery("SELECT p FROM Customer c JOIN Policy p ON c.id = p.customer.id WHERE p.customer.id = : customer_id")
                .setParameter("customer_id", 1L)
                .getResultList();
    }

}
