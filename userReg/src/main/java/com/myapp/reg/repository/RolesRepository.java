package com.myapp.reg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.reg.model.Role;
import com.myapp.reg.model.User;

public interface  RolesRepository  extends JpaRepository<Role, Integer>{
	
	
	Optional<Role> findByName(String name);

}
