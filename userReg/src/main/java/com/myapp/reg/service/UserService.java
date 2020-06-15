package com.myapp.reg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myapp.reg.dto.UserDto;
import com.myapp.reg.model.Role;
import com.myapp.reg.model.User;
import com.myapp.reg.repository.RolesRepository;
import com.myapp.reg.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RolesRepository roleRepo;

	public void adduser(UserDto udto) {

		User u = new User();

		u.setUsername(udto.getUsername());
		u.setEmail(udto.getEmail());
		u.setAccountNonExpired(true);
		u.setAccountNonLocked(true);
		u.setCredentialsNonExpired(true);
		u.setEnabled(true);
		u.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(udto.getPassword()));
		
		Role role = roleRepo.findByName(udto.getRole()).get();
		
		List<Role> list = new ArrayList<>();
		list.add(role);
		u.setRoles(list);
		
		u = userRepo.save(u);
		System.out.println(udto) ;
		System.out.println(u);

	}

}
