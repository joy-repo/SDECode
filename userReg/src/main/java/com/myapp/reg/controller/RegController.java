package com.myapp.reg.controller;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.reg.dto.UserDto;
import com.myapp.reg.model.TokenReg;
import com.myapp.reg.model.User;
import com.myapp.reg.repository.TokenRegRepository;
import com.myapp.reg.repository.UserRepository;
import com.myapp.reg.service.UserService;

@Controller("/")
public class RegController {

	@Autowired
	UserService us;
	
	@Autowired
	TokenRegRepository repo;
	
	@Autowired
	UserRepository userRepo;

	@GetMapping("/")
	public ModelAndView getRegPage() {
		ModelAndView modelAndView = new ModelAndView("Registration");
		modelAndView.addObject("userDto", new UserDto());
		return modelAndView;
	}

	@PostMapping("/register")
	public String register(UserDto udto) {
		System.out.println(udto);
		User u = us.adduser(udto);
		us.createToken(u);
		return "done";

	}
	
	@GetMapping("/confirm/{token}")
	public String confirm(@PathVariable String token) {
		
		Optional<TokenReg> t = repo.findByToken(token);
		
		if(t.isPresent()) {
			User u = userRepo.findById(t.get().getUserId()).get();
			u.setEnabled(true);
			userRepo.save(u);
			return "success";
		}
		return "failure";
		
		
		
		
		
	}
}
