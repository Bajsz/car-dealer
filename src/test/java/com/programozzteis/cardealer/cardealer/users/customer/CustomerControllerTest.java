package com.programozzteis.cardealer.cardealer.users.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.car.CarRepository;
import com.programozzteis.cardealer.cardealer.users.salesman.Salesman;


@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

	private static final int TEST_CUSTOMER_ID = 1;
	private static final int TEST_CAR_ID = 1;
	
	private static final int TEST_CUSTOMER_MONEY = 100;
	private static final int TEST_CAR_PRICE = 40;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerRepository customerRepo;
	@MockBean
	private CarRepository carRepo;
	
	private Car testCar;
	
	@BeforeEach
	/**
	 * Create a Test Customer
	 * Create a Test Car
	 */
	void setup()
	{
		Customer testCustomer = new Customer();
		testCustomer.setId(TEST_CUSTOMER_ID);
		testCustomer.setName("Test Customer");
		testCustomer.setCurrentMoney(TEST_CUSTOMER_MONEY);
		given(this.customerRepo.findById(TEST_CUSTOMER_ID)).willReturn(testCustomer);
		
		testCar = new Car();
		testCar.setId(TEST_CAR_ID);
		testCar.setConsumption("9");
		testCar.setPower("110");
		testCar.setPrice(TEST_CAR_PRICE);
		testCar.setProdYear(LocalDate.now());
		testCar.setSalesman(new Salesman());
		testCar.setType(null); // not relevant
		given(this.carRepo.findById(TEST_CAR_ID)).willReturn(testCar);
	}
	
	@Test
	/**
	 * By default, Customer does not exist
	 * 
	 * @expected
	 * 	NestedServletException
	 */
	void testInvalidCustomerPage()
	{
		assertThrows(NestedServletException.class, 
				() -> {
					mockMvc.perform(get("/customer/{custId}", TEST_CUSTOMER_ID));
				});
	}
	
	@Test
	/**
	 * Show all Cars for Test Customer
	 * 
	 * @expected
	 * 	status	--> OK
	 * 	model	--> Shopping is done
	 * 	view	--> customers/customerDetails
	 */
	void testAllCarOnCustomerPage() throws Exception
	{
		given(this.customerRepo.existsById(TEST_CUSTOMER_ID)).willReturn(true);

		mockMvc.perform(get("/customer/{custId}", TEST_CUSTOMER_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("customer"))
			.andExpect(model().attribute("customer", hasProperty("id", is(TEST_CUSTOMER_ID))))
			.andExpect(model().attributeExists("cars"))
			.andExpect(view().name("customers/customerDetails"));
	}
	
	@Test
	/**
	 * By default, Customer and Car do not exist
	 * 
	 * @expected
	 * 	NestedServletException
	 */
	void testInvalidBuyAction()
	{
		assertThrows(NestedServletException.class, 
				() -> {
					mockMvc.perform(get("/customer/{custId}/buycar/{carId}", TEST_CUSTOMER_ID, TEST_CAR_ID));
				});
	}
	
	@Test
	/**
	 * Prepare to Buy the Test Car
	 * 
	 * @expected
	 * 	status	--> OK
	 * 	model	--> Shopping is done
	 * 	view	--> customers/customerDetails
	 */
	void testSuccessBuyCar() throws Exception
	{
		given(this.customerRepo.existsById(TEST_CUSTOMER_ID)).willReturn(true);
		given(this.carRepo.existsById(TEST_CAR_ID)).willReturn(true);

		mockMvc.perform(get("/customer/{custId}/buycar/{carId}", TEST_CUSTOMER_ID, TEST_CAR_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("customer"))
			.andExpect(model().attribute("customer", hasProperty("currentMoney", is(TEST_CUSTOMER_MONEY-TEST_CAR_PRICE))))
			.andExpect(model().attributeExists("cars"))
			.andExpect(model().attributeExists("goodNews"))
			.andExpect(model().attribute("goodNews", "Congratulation for your new " + testCar.getType() + " Car!"))
			.andExpect(view().name("customers/customerDetails"));
	}
	
	@Test
	/**
	 * By default, Customer does not exist
	 * 
	 * @expected
	 * 	NestedServletException
	 */
	void testInvalidEditAction()
	{
		assertThrows(NestedServletException.class, 
				() -> {
					mockMvc.perform(get("/customer/{custId}/edit", TEST_CUSTOMER_ID));
				});
	}
	
	@Test
	/**
	 * Prepare to update existing Customer
	 * 
	 * @expected
	 * 	status	--> OK
	 * 	model	--> Available Customer
	 * 	view	--> customers/updateCustomerForm
	 */
	void testValidEditAction() throws Exception
	{
		given(this.customerRepo.existsById(TEST_CUSTOMER_ID)).willReturn(true);

		mockMvc.perform(get("/customer/{custId}/edit", TEST_CUSTOMER_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("customer"))
			.andExpect(model().attribute("customer", hasProperty("id", is(TEST_CUSTOMER_ID))))
			.andExpect(view().name("customers/updateCustomerForm"));
	}
	
	@Test
	/**
	 * Update done successfully
	 * 
	 * @expected
	 * 	status	--> OK / Redirect
	 * 	view	--> redirect:/customer/{custId}
	 */
	void testSuccessEditAction() throws Exception
	{
		mockMvc.perform(post("/customer/{custId}/edit", TEST_CUSTOMER_ID))
			.andExpect(status().is(302))
			.andExpect(model().size(0))
			.andExpect(view().name("redirect:/customer/{custId}"));
	}
}
