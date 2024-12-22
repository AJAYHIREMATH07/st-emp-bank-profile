package com.bankX.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankX.bank.service.TransactionService;

@RestController
@RequestMapping("/api/transfer")
public class TransactionController {

	
	@Autowired
	private TransactionService transactionService;
	
    @PostMapping("/transferFounds")
    public void transferFunds(@RequestParam Long fromAccountId,
                              @RequestParam Long toAccountId,
                              @RequestParam double amount) {
    	try {
			transactionService.transferMoney(fromAccountId, toAccountId, amount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
