package com.example.beautydiary.services;

import com.example.beautydiary.entities.Customer;
import com.example.beautydiary.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    public Customer getById(Long id) {
        return customerRepository.getReferenceById(id);
    }
}
