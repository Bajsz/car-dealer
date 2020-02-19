package com.programozzteis.cardealer.cardealer.system;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.programozzteis.cardealer.cardealer.users.User;
import com.programozzteis.cardealer.cardealer.users.UserRepository;


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
		Iterable<User> users = this.userRepo.findAll();
		model.put("users", users);

		return "welcome";
	}

}
