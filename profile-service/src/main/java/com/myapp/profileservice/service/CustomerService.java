package com.myapp.profileservice.service;

import java.util.List;

import com.myapp.profileservice.model.Customer;

public interface CustomerService {


    Customer save(Customer customer);

    Customer fetchById(int profileId);

    List<Customer> fetchAllProfiles();
}
