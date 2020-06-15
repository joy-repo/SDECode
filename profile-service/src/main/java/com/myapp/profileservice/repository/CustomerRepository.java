package com.myapp.profileservice.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.profileservice.model.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
}
