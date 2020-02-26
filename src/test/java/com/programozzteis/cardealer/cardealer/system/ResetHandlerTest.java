package com.programozzteis.cardealer.cardealer.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(value = ResetHandler.class)
public class ResetHandlerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	/**
	 * Test Reset Menu Item handler
	 * @throws Exception
	 * @expected
	 * 	status	--> OK
	 * 	model	--> Empty
	 * 	view	--> HomePage (/)
	 */
	void testResetHandler() throws Exception
	{
		mockMvc.perform(get("/reset"))
			.andExpect(status().is(302))
			.andExpect(model().size(0))
			.andExpect(view().name("redirect:/"));
	}
	
}
