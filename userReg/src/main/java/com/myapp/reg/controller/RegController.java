package com.myapp.reg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.reg.dto.UserDto;
import com.myapp.reg.service.UserService;

@Controller("/")
public class RegController {

	@Autowired
	UserService us;

	@GetMapping("/")
	public ModelAndView getRegPage() {
		ModelAndView modelAndView = new ModelAndView("Registration");
		modelAndView.addObject("userDto", new UserDto());
		return modelAndView;
	}

	@PostMapping("/register")
	public String register(UserDto udto) {
		System.out.println(udto);
		us.adduser(udto);
		return "done";

	}
}
