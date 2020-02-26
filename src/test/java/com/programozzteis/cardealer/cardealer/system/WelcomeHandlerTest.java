package com.programozzteis.cardealer.cardealer.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.programozzteis.cardealer.cardealer.users.admin.AdminRepository;
import com.programozzteis.cardealer.cardealer.users.customer.CustomerRepository;
import com.programozzteis.cardealer.cardealer.users.salesman.SalesmanRepository;

@WebMvcTest(value = WelcomeHandler.class)
public class WelcomeHandlerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminRepository adminRepo;
	@MockBean
	private CustomerRepository customerRepo;
	@MockBean
	private SalesmanRepository salesmanRepo;
	
	
	@Test
	/**
	 * Test for Home Page Handler
	 * @throws Exception
	 * @expected
	 * 	status	--> OK
	 * 	model	--> Contains all admin, salesman and customer
	 * 	view	--> Home Page (/)
	 */
	void testWelcomeHandler() throws Exception
	{
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(model().size(3))
			.andExpect(model().attributeExists("admins"))
			.andExpect(model().attributeExists("salesmans"))
			.andExpect(model().attributeExists("customers"))
			.andExpect(view().name("welcome"));
	}
}
