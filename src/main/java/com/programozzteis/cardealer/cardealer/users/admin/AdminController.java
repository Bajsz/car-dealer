package com.programozzteis.cardealer.cardealer.users.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.car.CarRepository;
import com.programozzteis.cardealer.cardealer.logger.CarDealerLogger;
import com.programozzteis.cardealer.cardealer.users.customer.Customer;
import com.programozzteis.cardealer.cardealer.users.customer.CustomerRepository;
import com.programozzteis.cardealer.cardealer.users.salesman.Salesman;
import com.programozzteis.cardealer.cardealer.users.salesman.SalesmanRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final CustomerRepository customerRepo;
	private final SalesmanRepository salesmanRepo;
	private final CarRepository carRepo;
	private final AdminRepository adminRepo;


	public AdminController(CustomerRepository customerRepo, SalesmanRepository salesmanRepo, CarRepository carRepo,
			AdminRepository adminRepo) {
		this.customerRepo = customerRepo;
		this.salesmanRepo = salesmanRepo;
		this.carRepo = carRepo;
		this.adminRepo = adminRepo;
	}

	@GetMapping("/{adminId}")
	public String welcome(@PathVariable(name = "adminId") int adminId, Map<String, Object> model)
	{
		/** show all default users */
		model.put("adminId", adminId);
		
		/** SALESMANS */
		Iterable<Salesman> salesmans = this.salesmanRepo.findAll();
		model.put("salesmans", salesmans);
		
		/** CUSTOMER */
		Iterable<Customer> customers = this.customerRepo.findAll();
		model.put("customers", customers);
		
		/** ADVERTISEMENTS */
		List<Car> cars = this.carRepo.findAll();
		model.put("cars", cars);
		
		
		return "admins/adminDetails";
	}
	
	@GetMapping("/{adminId}/deletecust/{custId}")
	public String deleteCustomer(
			@PathVariable(name = "adminId") int adminId,
			@PathVariable(name = "custId") int customerId,
			Map<String, Object> model)
	{
		/** Check Params */
		if(this.adminRepo.existsById(adminId)
				&& this.customerRepo.existsById(customerId))
		{
			/** Delete */
			this.customerRepo.deleteById(customerId);
			
			/** Prepare for Admin Panel */
			welcome(adminId, model);
		}
		else
		{
			/** Customer or Admin not registered --> ERROR */
			RuntimeException rEx = new RuntimeException("Requested Customer or Admin by URL ID is unknown");
			CarDealerLogger.getLogger().error(rEx);
			throw rEx;
		}
		
		/** Go Back to Admin Panel */
		return "admins/adminDetails";
	}
	
	@GetMapping("/{adminId}/deletecar/{carId}")
	public String deleteCar(
			@PathVariable(name = "adminId") int adminId,
			@PathVariable(name = "carId") int carId,
			Map<String, Object> model)
	{
		/** Check Params */
		if(this.adminRepo.existsById(adminId)
				&& this.carRepo.existsById(carId))
		{
			/** Delete */
			this.carRepo.deleteById(carId);
			
			/** Prepare for Admin Panel */
			welcome(adminId, model);
		}
		else
		{
			/** Customer or Admin not registered --> ERROR */
			RuntimeException rEx = new RuntimeException("Requested Car or Admin by URL ID is unknown");
			CarDealerLogger.getLogger().error(rEx);
			throw rEx;
		}
		
		/** Go Back to Admin Panel */
		return "admins/adminDetails";
	}
	
	@GetMapping("/{adminId}/deletesman/{smanId}")
	public String deleteSalesman(
			@PathVariable(name = "adminId") int adminId,
			@PathVariable(name = "smanId") int salesmanId,
			Map<String, Object> model)
	{
		/** Check Params */
		if(this.adminRepo.existsById(adminId)
				&& this.salesmanRepo.existsById(salesmanId))
		{
			/** Delete Cars of Salesman */
			List<Car> cars = this.carRepo.findAll();
			cars.stream()
				.filter(car -> (car.getSalesman().getId() == salesmanId))
				.forEach(this.carRepo::delete);
			
			/** Delete Salesman */
			this.salesmanRepo.deleteById(salesmanId);
			
			/** Prepare for Admin Panel */
			welcome(adminId, model);
		}
		else
		{
			/** Customer or Admin not registered --> ERROR */
			RuntimeException rEx = new RuntimeException("Requested Car or Admin by URL ID is unknown");
			CarDealerLogger.getLogger().error(rEx);
			throw rEx;
		}
		
		/** Go Back to Admin Panel */
		return "admins/adminDetails";
	}
	
}
