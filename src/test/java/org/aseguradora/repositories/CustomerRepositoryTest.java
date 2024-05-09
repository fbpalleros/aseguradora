package org.aseguradora.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.aseguradora.controllers.CotizacionController;
import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.services.CarService;
import org.aseguradora.services.CustomerService;
import org.aseguradora.services.InsuranceService;
import org.aseguradora.services.PolicyService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@TestInstance(Lifecycle.PER_CLASS)
class CustomerRepositoryTest {

	static private CustomerRepository customerRepostory;
	
	private Long ID_TEST = 1L;
	
	@BeforeAll
	public static void init() {
        customerRepostory = mock(CustomerRepository.class);
    }
	
	@Test
	public void create() {
		 Customer customer = new Customer(ID_TEST, "Test Name", "test2@gmail.com");
		 customerRepostory.save(customer);
		 assertNotNull(customer);
	 }
	
	 @Test
	 public void findByid() {
		 Customer customer = customerRepostory.findOne(ID_TEST);
		 assertNotNull(customer);
	 }
	
	
	 @Test
	 public void findAll() {
		 List<Customer> customer = customerRepostory.findAll();
		 assertNotNull(customer);
	 }
}
