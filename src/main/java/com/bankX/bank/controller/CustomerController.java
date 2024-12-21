package com.bankX.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankX.bank.entity.CustomerEntity;
import com.bankX.bank.service.CustomerService;
import com.bankX.bank.service.TransactionService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	 private CustomerService customerService;
	
    @PostMapping("/onboard")
    public CustomerEntity onboardCustomer(@RequestParam String name) {
         try {
			CustomerEntity onboardCustomer = customerService.onboardCustomer(name);
			 
			 return onboardCustomer;
		} catch (Exception e) {
			
			
		}
		return null;
    }

}
