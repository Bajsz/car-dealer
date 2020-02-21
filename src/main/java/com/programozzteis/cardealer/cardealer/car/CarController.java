package com.programozzteis.cardealer.cardealer.car;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

	private final CarRepository carRepo;
	
	public CarController(CarRepository carRepo) {
		this.carRepo = carRepo;
	}


	@GetMapping("/advertisementList.html")
	public String showAdvertisementList(Map<String, Object> model) {
		model.put("cars", this.carRepo.findAll());
		return "advertisements/advertisementList";
	}
	
}
