package com.bankX.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
