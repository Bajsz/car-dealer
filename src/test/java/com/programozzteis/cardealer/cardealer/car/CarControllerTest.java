package com.programozzteis.cardealer.cardealer.car;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(value = CarController.class)
public class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CarRepository carRepo;
	
	@Test
	/**
	 * Test the Handler of /advertisementList.html
	 * @throws Exception
	 * @expected
	 * 	status 	--> OK
	 * 	view 	--> advertisements/advertisementList
	 * 	model 	--> contains "cars" data
	 */
	void testShowAdvertisementList() throws Exception
	{
		mockMvc.perform(get("/advertisementList.html"))
			.andExpect(status().isOk())
			.andExpect(view().name("advertisements/advertisementList"))
			.andExpect(model().attributeExists("cars"));
	}
}
