package com.programozzteis.cardealer.cardealer.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeHandler {
	
	@GetMapping("/")
	public String welcome()
	{
		return "welcome";
	}

}
