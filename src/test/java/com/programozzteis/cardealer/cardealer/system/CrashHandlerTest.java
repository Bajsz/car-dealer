package com.programozzteis.cardealer.cardealer.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Test;


@WebMvcTest(value = CrashHandler.class)
public class CrashHandlerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testCrashHandler()
	{
		assertThrows(NestedServletException.class, 
				() -> {
					mockMvc.perform(get("/oups"));
				});
	}
}
