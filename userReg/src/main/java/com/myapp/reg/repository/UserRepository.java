package com.myapp.reg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.reg.model.User;



public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findByUsername(String name);

}
