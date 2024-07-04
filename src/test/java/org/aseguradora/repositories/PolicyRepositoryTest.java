package org.aseguradora.repositories;


import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.impl.CustomerRepositoryImpl;
import org.aseguradora.repositories.impl.PolicyRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, CustomerRepositoryImpl.class, PolicyRepositoryImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PolicyRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private PolicyRepository policyRepository;

    private CustomerRepository customerRepository;
    private InsuranceRepository insuranceRepository;

    @BeforeEach
    public void init() {
        this.customerRepository = Mockito.mock(CustomerRepository.class);
        this.insuranceRepository = Mockito.mock(InsuranceRepository.class);
        this.policyRepository = new PolicyRepositoryImpl(this.sessionFactory);
        dadoQueExistenPolizas();
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaPoliza() {
        Customer customer = this.customerRepository.findOne(1L);

        Insurance insurance = this.insuranceRepository.findById(2L);

        Policy policy = new Policy();
        policy.setCustomer(customer);
        policy.setInsurance(insurance);

        Mockito.when(this.customerRepository.findOne(1L)).thenReturn(customer);
        Mockito.when(this.insuranceRepository.findById(2L)).thenReturn(insurance);

        this.policyRepository.save(policy);

        Policy policyObtain = (Policy) this.sessionFactory.getCurrentSession()
										                  .createQuery("FROM Policy WHERE id = 4")
										                  .getSingleResult();

        assertThat(policyObtain, equalTo(policy));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObteganLasPolizasDeUnCliente() {

        List<Policy> policiesObtains = this.policyRepository.findByCustomerId(1L);

        assertThat(policiesObtains.size(), equalTo(2));

    }

    private List<Policy> dadoQueExistenPolizas() {
        Customer c1 = new Customer(1L, "John", "John@gmail.com", "12345");
        Customer c2 = this.customerRepository.findOne(2L);
        Mockito.when(this.customerRepository.findOne(1L)).thenReturn(c1);
        Mockito.when(this.customerRepository.findOne(2L)).thenReturn(c2);

        Policy p1 = new Policy(1L, 120000.00);
        p1.setCustomer(c1);
        Policy p2 = new Policy(1L, 1000.00);
        p2.setCustomer(c1);
        Policy p3 = new Policy(1L, 8000.00);
        p3.setCustomer(c2);
//
//        this.policyRepository.save(p1);
//        this.policyRepository.save(p2);
//        this.policyRepository.save(p3);
        this.sessionFactory.getCurrentSession().save(c1);
        this.sessionFactory.getCurrentSession().save(p1);
        this.sessionFactory.getCurrentSession().save(p2);
        this.sessionFactory.getCurrentSession().save(p3);
        
        return Arrays.asList(p1, p2, p3);

    }


}
