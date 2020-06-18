package com.myapp.reg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myapp.reg.dto.UserDto;
import com.myapp.reg.model.Role;
import com.myapp.reg.model.TokenReg;
import com.myapp.reg.model.User;
import com.myapp.reg.repository.RolesRepository;
import com.myapp.reg.repository.TokenRegRepository;
import com.myapp.reg.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	RolesRepository roleRepo;

	@Autowired
	TokenRegRepository tokenRepo;

	@Autowired
	private JavaMailSender javaMailSender;

	public User adduser(UserDto udto) {

		User u = new User();

		u.setUsername(udto.getUsername());
		u.setEmail(udto.getEmail());
		u.setAccountNonExpired(true);
		u.setAccountNonLocked(true);
		u.setCredentialsNonExpired(true);
		u.setEnabled(false);
		u.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(udto.getPassword()));

		Role role = roleRepo.findByName(udto.getRole()).get();

		List<Role> list = new ArrayList<>();
		list.add(role);
		u.setRoles(list);

		u = userRepo.save(u);
		System.out.println(udto);
		System.out.println(u);
		return u;

	}

	public void createToken(User u) {

		String uuid = UUID.randomUUID().toString();
		TokenReg reg = new TokenReg();

		reg.setToken(uuid);
		reg.setUserId(u.getId());
		reg.setEnabled(true);
		String message = "localhost:8787/confirm/" + uuid;
		System.out.println(message);

		sendEmail(u.getEmail(), message);

		tokenRepo.save(reg);

	}

	void sendEmail(String email, String message) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(email);

			msg.setSubject("Registration for:" + email);
			msg.setText("Please cklick here to confirm-> "+message);

			javaMailSender.send(msg);
		} catch (Exception e) {
			System.out.println(message);
		}

	}

}
