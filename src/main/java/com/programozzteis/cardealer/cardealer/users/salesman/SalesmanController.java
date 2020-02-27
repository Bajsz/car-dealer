package com.programozzteis.cardealer.cardealer.users.salesman;

import java.util.stream.Collectors;

import javax.validation.Valid;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.car.CarRepository;
import com.programozzteis.cardealer.cardealer.logger.CarDealerLogger;

@Controller
@RequestMapping("/salesman")
public class SalesmanController {
	
	private final CarRepository carRepo;
	private final SalesmanRepository salesmanRepo;

	
	public SalesmanController(CarRepository carRepo, SalesmanRepository salesmanRepo) {
		this.carRepo = carRepo;
		this.salesmanRepo = salesmanRepo;
	}

	@GetMapping("/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("salesman", new Salesman());
		return "salesman/findSalesman";
	}
	
	@GetMapping("")
	public String processFindForm(Salesman salesman, Map<String, Object> model) 
	{
		String destinationURL = "salesman/findSalesman";
		String searchResult = "";

		if (salesman.getName().isEmpty() == false) {
			
			// find salesman by name
			Collection<Salesman> results = this.salesmanRepo.findByName(salesman.getName());
			if (results.isEmpty()) {
				// no salesman found
				destinationURL = "salesman/findSalesman";
				searchResult = "No Salesman found. Try again with an other Name...";
			}
			else if (results.size() == 1) {
				// 1 salesman found
				salesman = results.iterator().next();
				destinationURL = "redirect:/salesman/" + salesman.getId();
			}
			else
			{
				/** Multiple salesman use-case is not implemented */
				searchResult = "More Salesman found. Please use more precise name...";
				CarDealerLogger.getLogger().error("Multiple Salesman found by Searching use-case is not implemented");
			}
			
		}
		else {
			/** Empty Name use-case is not implemented */
			searchResult = "Please add a Name for searching...";
			CarDealerLogger.getLogger().error("Search with Empty Name use-case is not implemented");
		}
		
		
		model.put("searchResult", searchResult);
		
		return destinationURL;
	}
	
	@GetMapping("/{salesmanId}")
	public String showMyAds(@PathVariable(name = "salesmanId") int salesmanId, Map<String, Object> model)
	{
		if(this.salesmanRepo.existsById(salesmanId))
		{
			/** Customer exists with ID */
			Salesman sman = this.salesmanRepo.findById(salesmanId);
			model.put("salesman", sman);
					
			List<Car> cars = sman.getCars();
			
			
			/** Other solution is to Get All Car from DB then Filter */
//			List<Car> cars = this.carRepo.findAll();
//			cars = cars.stream()
//					.filter(car -> car.getSalesman().getId() == salesmanId)
//					.collect(Collectors.toList());		
			
			model.put("cars", cars);
		}
		else
		{
			/** Salesman not registered --> ERROR */
			RuntimeException rEx = new RuntimeException("Requested Salesman by URL ID is unknown");
			CarDealerLogger.getLogger().error(rEx);
			throw rEx;
		}
		
		
		return "salesman/salesmanDetails";
	}
	
	@GetMapping("/*/new")
	public String addNewAd(Map<String, Object> model)
	{
		/** Prepare the new Car */
		Car newCar = new Car();
		model.put("car", newCar);
		
		/** Provide the available Car Types via Car Obj */
		model.put("car_types", newCar.populateCarTypes());
		
		return "salesman/createNewAd";
	}
	
	
	@PostMapping("/{salesmanId}/new")
	public String finalizeNewAd(
			@Valid Car car,
			@PathVariable(name = "salesmanId") int salesmanId,
			Map<String, Object> model)
	{
		if(this.salesmanRepo.existsById(salesmanId))
		{
			/** Put new Car to DB */
			Salesman sman = this.salesmanRepo.findById(salesmanId);
			sman.addCar(car);
			
			this.carRepo.save(car);
		}
		else
		{
			/** Salesman not registered --> ERROR */
			RuntimeException rEx = new RuntimeException("Requested Salesman by URL ID is unknown");
			CarDealerLogger.getLogger().error(rEx);
			throw rEx;
		}
		
		/** Upload model for Salesman Page */
		showMyAds(salesmanId, model);
		
		/** Redirect to Salesman Page */
		return "redirect:/salesman/{salesmanId}";
	}
}
