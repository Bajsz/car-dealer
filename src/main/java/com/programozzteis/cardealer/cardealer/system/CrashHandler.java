package com.programozzteis.cardealer.cardealer.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrashHandler {

	@GetMapping("/oups")
	public void doCrash()
	{
		throw new RuntimeException("Demo Crash triggered and catched by Error Page");
	}
}
