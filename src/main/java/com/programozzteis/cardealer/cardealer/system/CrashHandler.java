package com.programozzteis.cardealer.cardealer.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.programozzteis.cardealer.cardealer.logger.CarDealerLogger;

@Controller
public class CrashHandler {

	@GetMapping("/oups")
	public void doCrash()
	{
		RuntimeException rEx = new RuntimeException("Demo Crash triggered and catched by Error Page");
		CarDealerLogger.getLogger().error(rEx);
		throw rEx;
	}
}
