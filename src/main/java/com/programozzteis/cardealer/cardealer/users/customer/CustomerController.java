package com.programozzteis.cardealer.cardealer.users.customer;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.car.CarRepository;
import com.programozzteis.cardealer.cardealer.logger.CarDealerLogger;

@Controller
public class CustomerController {

	private final CustomerRepository customerRepo;
	private final CarRepository carRepo;
	
	public CustomerController(CustomerRepository customerRepo, CarRepository carRepo) {
		this.customerRepo = customerRepo;
		this.carRepo = carRepo;
	}


	@GetMapping("/customer/{custId}")
	public String listAllCars(@PathVariable(name = "custId") int custId, Map<String, Object> model)
	{
		if(this.customerRepo.existsById(custId))
		{
			/** Customer exists with ID */
			Customer customer = this.customerRepo.findById(custId);
			model.put("customer", customer);
			
			/** Show all Adds */
			List<Car> cars = this.carRepo.findAll();
			model.put("cars", cars);
		}
		else
		{
			/** Customer not registered --> ERROR */
			RuntimeException rEx = new RuntimeException("Requested Customer by URL ID is unknown");
			CarDealerLogger.getLogger().error(rEx);
			throw rEx;
		}
		
		
		return "customers/customerDetails";
	}
	
	
	@GetMapping("/customer/{custId}/buycar/{carId}")
	public String buyCarById(
			@PathVariable(name = "custId") int custId, 
			@PathVariable(name = "carId") int carId, 
			Map<String, Object> model)
	{
		if((this.customerRepo.existsById(custId)) && (this.carRepo.existsById(carId)))
		{
			/** Customer and Car exists with ID */
			Customer cust = this.customerRepo.findById(custId);
			Car car = this.carRepo.findById(carId);
			
			int customerMoney = cust.getCurrentMoney();
			int carPrice = car.getPrice();
			
			/** Customer has enough money for the Car? */
			if(customerMoney >= carPrice)
			{
				/** Decrease Customer's money */
				cust.setCurrentMoney(customerMoney-carPrice);
				
				/** Delete Car from DB */
				this.carRepo.delete(car);
				
				/** Feedback about the success purchasing */
				model.put("purchasing", "Congratulation for your new Car!");
			}
			else
			{
				/** Feedback about not enough money */
				model.put("purchasing", "Unfortunately you have no enough money for this Car...");
			}
			model.put("customer", cust);
			
			
			/** Show all remaining Adds */
			List<Car> cars = this.carRepo.findAll();
			model.put("cars", cars);
		}
		else
		{
			/** Car not registered --> ERROR */
			throw new RuntimeException("Requested Car is unknown and not available");
		}
		
		return "customers/customerDetails";
		
	}
	
	
	@GetMapping("/customer/{custId}/edit")
	public String initEditForm(@PathVariable(name = "custId") int custId, Map<String, Object> model) 
	{
		if(this.customerRepo.existsById(custId))
		{
			/** Customer exists with ID */
			Customer customer = this.customerRepo.findById(custId);
			model.put("customer", customer);
		}
		else
		{
			/** Customer not registered --> ERROR */
			RuntimeException rEx = new RuntimeException("Requested Customer by URL ID is unknown");
			CarDealerLogger.getLogger().error(rEx);
			throw rEx;
		}
		
		return "customers/updateCustomerForm";
	}
	
	@PostMapping("/customer/{custId}/edit")
	public String initEditForm(
			@Valid Customer cust,
			@PathVariable(name = "custId") int custId,
			Map<String, Object> model) 
	{
		cust.setId(custId);
		this.customerRepo.save(cust);
		
		return "redirect:/customer/{custId}";
	}
}
