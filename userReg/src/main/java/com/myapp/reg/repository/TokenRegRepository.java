package com.myapp.reg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.reg.model.TokenReg;

public interface TokenRegRepository extends JpaRepository<TokenReg,Integer>{

	Optional<TokenReg> findByToken(String token);
}
//