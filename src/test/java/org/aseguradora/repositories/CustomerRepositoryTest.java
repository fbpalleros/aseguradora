package org.aseguradora.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.Customer;
import org.aseguradora.repositories.impl.CustomerRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
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
