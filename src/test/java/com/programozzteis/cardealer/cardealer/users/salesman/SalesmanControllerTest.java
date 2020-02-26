package com.programozzteis.cardealer.cardealer.users.salesman;

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

import org.junit.jupiter.api.Test;

import com.programozzteis.cardealer.cardealer.car.CarRepository;
import com.programozzteis.cardealer.cardealer.users.salesman.Salesman;


@WebMvcTest(value = SalesmanController.class)
public class SalesmanControllerTest {

	private static final int TEST_SALESMAN_ID = 1;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	private CarRepository carRepo;
	@MockBean
	private SalesmanRepository salesmanRepo;
	
	
	@Test
	void testPrepareFindForm() throws Exception
	{
		mockMvc.perform(get("/salesman/find"))
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("salesman"))
			.andExpect(view().name("salesman/findSalesman"));
	}
	
	@Test
	void testShowInvalidSalesmanAds() throws Exception
	{
		assertThrows(NestedServletException.class, 
				() -> {
					mockMvc.perform(get("/salesman/{salesmanId}", TEST_SALESMAN_ID));
				});
	}
	
	@Test
	void testShowValidSalesmanAds() throws Exception
	{
		setup();		
		
		mockMvc.perform(get("/salesman/{salesmanId}", TEST_SALESMAN_ID))
			.andExpect(status().isOk())
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("salesman"))
			.andExpect(model().attribute("salesman", hasProperty("id", is(TEST_SALESMAN_ID))))
			.andExpect(model().attributeExists("cars"))
			.andExpect(view().name("salesman/salesmanDetails"));
	}
	
	@Test
	void testCreateNewAd() throws Exception
	{	
		mockMvc.perform(get("/salesman/*/new"))
			.andExpect(status().isOk())
			.andExpect(model().size(2))
			.andExpect(model().attributeExists("car"))
			.andExpect(model().attributeExists("car_types"))
			.andExpect(view().name("salesman/createNewAd"));
	}
	
	@Test
	void testFinalizeNewAdWithInvalidSalesman() throws Exception
	{
		assertThrows(NestedServletException.class, 
				() -> {
					mockMvc.perform(post("/salesman/{salesmanId}/new", TEST_SALESMAN_ID));
				});
	}
	
	@Test
	void testFinalizeNewAdWithValidSalesman() throws Exception
	{
		setup();		
		
		mockMvc.perform(post("/salesman/{salesmanId}/new", TEST_SALESMAN_ID))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/salesman/{salesmanId}"));
	}
	
	void setup()
	{
		Salesman testSalesman = new Salesman();
		testSalesman.setId(TEST_SALESMAN_ID);
		
		given(this.salesmanRepo.existsById(TEST_SALESMAN_ID)).willReturn(true);
		given(this.salesmanRepo.findById(TEST_SALESMAN_ID)).willReturn(testSalesman);
	}
}
