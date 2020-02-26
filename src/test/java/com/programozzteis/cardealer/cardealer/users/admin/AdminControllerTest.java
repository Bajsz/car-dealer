package com.programozzteis.cardealer.cardealer.users.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.programozzteis.cardealer.cardealer.car.CarRepository;
import com.programozzteis.cardealer.cardealer.users.customer.CustomerRepository;
import com.programozzteis.cardealer.cardealer.users.salesman.SalesmanRepository;

@WebMvcTest(value = AdminController.class)
public class AdminControllerTest {

	private static final int TEST_ADMIN_ID = 1;
	private static final int TEST_CUSTOMER_ID = 1;
	private static final int TEST_CAR_ID = 1;
	private static final int TEST_SALESMAN_ID = 1;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerRepository customerRepo;
	@MockBean
	private SalesmanRepository salesmanRepo;
	@MockBean
	private CarRepository carRepo;
	@MockBean
	private AdminRepository adminRepo;
	
	@Test
	/**
	 * Test the Admin Panel content
	 * 
	 * @throws Exception
	 * @expected
	 * 	status	--> OK
	 * 	model	--> Size:4 -> adminId, salesmans, customers, cars
	 * 	view	--> admins/adminDetails
	 */
	void testAdminPanel() throws Exception
	{
		mockMvc.perform(get("/admin/{adminId}", TEST_ADMIN_ID))
			.andExpect(status().isOk())
			.andExpect(model().size(4))
			.andExpect(model().attributeExists("adminId"))
			.andExpect(model().attribute("adminId", TEST_ADMIN_ID))
			.andExpect(model().attributeExists("salesmans"))
			.andExpect(model().attributeExists("customers"))
			.andExpect(model().attributeExists("cars"))
			.andExpect(view().name("admins/adminDetails"));
	}
	
	@Test
	/**
	 * Exception in case of InvalidID
	 * 
	 * @throws Exception
	 * @expected
	 * 		NestedServletException
	 */
	void testDeleteNotExistingCar() throws Exception
	{
		testInvalidDeleteCommand("deletecust", TEST_ADMIN_ID, TEST_CAR_ID);
	}
	
	@Test
	/**
	 * Success in case of Valid Admin and Car ID
	 * 
	 * @throws Exception
	 * @expected
	 * 		status, model and view
	 */
	void testDeleteCar() throws Exception
	{
		given(this.carRepo.existsById(TEST_CAR_ID)).willReturn(true);
		
		testDeleteCommand("deletecar", TEST_ADMIN_ID, TEST_CAR_ID);
	}
	
	@Test
	/**
	 * Exception in case of InvalidID
	 * 
	 * @throws Exception
	 * @expected
	 * 		NestedServletException
	 */
	void testDeleteNotExistingSalesman() throws Exception
	{
		testInvalidDeleteCommand("deletecust", TEST_ADMIN_ID, TEST_SALESMAN_ID);
	}
	
	@Test
	/**
	 * Success in case of Valid Admin and Salesman ID
	 * 
	 * @throws Exception
	 * @expected
	 * 		status, model and view
	 */
	void testDeleteSalesman() throws Exception
	{
		given(this.salesmanRepo.existsById(TEST_SALESMAN_ID)).willReturn(true);
	
		testDeleteCommand("deletesman", TEST_ADMIN_ID, TEST_SALESMAN_ID);
	}
	
	@Test
	/**
	 * Exception in case of InvalidID
	 * 
	 * @throws Exception
	 * @expected
	 * 		NestedServletException
	 */
	void testDeleteNotExistingCustomer() throws Exception
	{
		testInvalidDeleteCommand("deletecust", TEST_ADMIN_ID, TEST_CUSTOMER_ID);
	}
	
	@Test
	/**
	 * Success in case of Valid Admin and Customer ID
	 * 
	 * @throws Exception
	 * @expected
	 * 		status, model and view
	 */
	void testDeleteCustomer() throws Exception
	{
		given(this.customerRepo.existsById(TEST_CUSTOMER_ID)).willReturn(true);
		
		testDeleteCommand("deletecust", TEST_ADMIN_ID, TEST_CUSTOMER_ID);
	}
	
	
	/**
	 * Wrapper for Exception Test 
	 * 
	 * @param deleteURL
	 * @param adminId
	 * @param elementId
	 */
	void testInvalidDeleteCommand(final String deleteURL, final int adminId, final int elementId)
	{
		assertThrows(NestedServletException.class, 
				() -> {
					mockMvc.perform(get("/admin/{adminId}/" + deleteURL + "/{smanId}", adminId, elementId));
				});
	}
	
	/** 
	 * Wrapper for Success Delete Commands
	 * 
	 * @param deleteURL
	 * @param adminId
	 * @param elementId
	 * @throws Exception
	 */
	void testDeleteCommand(final String deleteURL, final int adminId, final int elementId) throws Exception
	{
		/** Stubs */
		given(this.adminRepo.existsById(adminId)).willReturn(true);
		
		/** Test */
		mockMvc.perform(get("/admin/{adminId}/" + deleteURL + "/{custId}", adminId, elementId))
			.andExpect(status().isOk())
			.andExpect(model().size(4))
			.andExpect(model().attributeExists("adminId"))
			.andExpect(model().attribute("adminId", adminId))
			.andExpect(model().attributeExists("salesmans"))
			.andExpect(model().attributeExists("customers"))
			.andExpect(model().attributeExists("cars"))
			.andExpect(view().name("admins/adminDetails"));
	}
}
