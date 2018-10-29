package com.abosancic.raspberrypi.repository;

import java.util.List;

import com.abosancic.raspberrypi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

   List<Customer> findByLastName(String lastName);
}