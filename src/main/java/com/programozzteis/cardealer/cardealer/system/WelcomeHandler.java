package com.programozzteis.cardealer.cardealer.system;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.programozzteis.cardealer.cardealer.users.User;
import com.programozzteis.cardealer.cardealer.users.UserRepository;
import com.programozzteis.cardealer.cardealer.users.UserRole;


@Controller
public class WelcomeHandler {
	
	private final UserRepository userRepo;
	
	public WelcomeHandler(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/")
	public String welcome(Map<String, Object> model)
	{
		/** show all default users */
		Iterable<User> usersIterable = this.userRepo.findAll();
		ArrayList<User> users = new ArrayList<User>();
		usersIterable.forEach(users::add);
		
		/** Filter for: ADMINS */
		Iterable<User> admins = users.stream()
				.filter(user -> (user.getRole() == UserRole.ADMIN))
				.collect(Collectors.toList());
		model.put("admins", admins);
		
		/** Filter for: SELLERS */
		Iterable<User> sellers = users.stream()
				.filter(user -> (user.getRole() == UserRole.SELLER))
				.collect(Collectors.toList());
		model.put("sellers", sellers);
		
		/** Filter for: BUYERS */
		Iterable<User> buyers = users.stream()
				.filter(user -> (user.getRole() == UserRole.BUYER))
				.collect(Collectors.toList());
		model.put("buyers", buyers);
		
		
		return "welcome";
	}

}
