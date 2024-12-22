package com.bankX.bank.controller;

import com.bankX.bank.Filter.CustomerFilters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
//	@GetMapping("/getCustomers/{customerFilters}")
	@GetMapping("/getCustomers")
	public Page<CustomerEntity> getCustomers(CustomerFilters customerFilters) throws JsonProcessingException {

//		ObjectMapper objectMapper = new ObjectMapper();
//		CustomerFilters customerFilters1 = objectMapper.readValue(customerFilters, CustomerFilters.class);
		Page<CustomerEntity> customers = customerService.getCustomers(customerFilters);
		return customers;
    }

}
