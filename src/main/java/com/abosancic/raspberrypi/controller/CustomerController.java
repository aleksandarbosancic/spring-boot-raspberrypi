package com.abosancic.raspberrypi.controller;

import com.abosancic.raspberrypi.entity.Customer;
import com.abosancic.raspberrypi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
class CustomerController {

   @Autowired
   CustomerService customerService;

   @GetMapping("/all")
   public List<Customer> getAllCustomers() {
      return customerService.getAllCustomers();
   }
}
