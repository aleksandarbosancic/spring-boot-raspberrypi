package com.abosancic.raspberrypi.service;

import com.abosancic.raspberrypi.entity.Customer;
import com.abosancic.raspberrypi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

   @Autowired
   CustomerRepository customerRepository;

   public List<Customer> getAllCustomers() {
      return customerRepository.findAll();
   }

   public Customer getCustomerById(Long id) {
      return customerRepository.findById( id ).orElse( null );
   }

}
