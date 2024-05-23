package org.aseguradora.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.aseguradora.config.HibernateConfig;
import org.aseguradora.controllers.CotizacionController;
import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.impl.CustomerRepositoryImpl;
import org.aseguradora.repositories.impl.PolicyRepositoryImpl;
import org.aseguradora.services.CarService;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.PolicyService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.PrepareTestInstance;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, CustomerRepositoryImpl.class})

class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;

	
	private Long ID_TEST = 1L;
	
	
	@Test
	public void create() {
		 Customer customer = new Customer(ID_TEST, "Test Name", "test2@gmail.com");
		 customerRepository.save(customer);
		 assertNotNull(customer);
	 }
	
	 @Test
	 public void findByid() {
		 Customer customer = customerRepository.findOne(ID_TEST);
		 assertNotNull(customer);
	 }
	
	
	 @Test
	 public void findAll() {
		 List<Customer> customer = customerRepository.findAll();
		 assertNotNull(customer);
	 }
}
