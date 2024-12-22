package com.bankX.bank.service;

import java.util.List;

import com.bankX.bank.Filter.CustomerFilters;
import com.bankX.bank.Specification.CustomerSpecifications;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bankX.bank.entity.AccountEntity;
import com.bankX.bank.entity.CustomerEntity;
import com.bankX.bank.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	 CustomerRepository customerRepository;
	
    public CustomerEntity onboardCustomer(String name) {
    	CustomerEntity customer = new CustomerEntity();
        customer.setName(name);

        AccountEntity currentAccount = new AccountEntity();
        currentAccount.setAccountType("Current");
        currentAccount.setBalance(0.0);

        AccountEntity savingsAccount = new AccountEntity();
        savingsAccount.setAccountType("Savings");
        savingsAccount.setBalance(500.0); // Joining bonus

        currentAccount.setCustomerEntity(customer);
        savingsAccount.setCustomerEntity(customer);
        
        customer.setAccountEntity(List.of(currentAccount, savingsAccount));

        return customerRepository.save(customer);
    }

    public Page<CustomerEntity> getCustomers(CustomerFilters customerFilters){
    int page = 0;
    int size = 1;
        Specification<CustomerEntity> spec = new CustomerSpecifications(customerFilters);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CustomerEntity> allCustomers = customerRepository.findAll(spec, pageRequest);
//        Page<CustomerEntity> allCustomers = customerRepository.findAll(spec);
//        List<CustomerEntity> all = customerRepository.findAll(spec);


        List<CustomerEntity> content = allCustomers.getContent();
        return allCustomers;
//        return null;


    }


}
