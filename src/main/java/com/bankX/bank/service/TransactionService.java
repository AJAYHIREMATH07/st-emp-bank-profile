package com.bankX.bank.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankX.bank.entity.AccountEntity;
import com.bankX.bank.entity.TransactionEntity;
import com.bankX.bank.exception.InsufficientBalanceException;
import com.bankX.bank.repository.AccountRepository;
import com.bankX.bank.repository.TransactionRepository;

import jakarta.transaction.Transactional;

public class TransactionService {
	@Autowired
    AccountRepository accountRepository;
	
	@Autowired
    TransactionRepository transactionRepository;
	
	@Autowired
	NotificationService notificationService;
    
    @Transactional
    public void transferMoney(Long fromAccountId, Long toAccountId, double amount) {
        AccountEntity fromAccount = accountRepository.findById(fromAccountId).orElseThrow();
        AccountEntity toAccount = accountRepository.findById(toAccountId)
        		.orElseThrow(()->new InsufficientBalanceException("toAccountId : " +toAccountId+"doesn't have amount "));

        if (fromAccount.getAccountType().equals("Current")) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            TransactionEntity debitTransaction = new TransactionEntity();
            debitTransaction.setAmount(amount);
            debitTransaction.setType("Debit");
            debitTransaction.setTimestamp(LocalDateTime.now());
            debitTransaction.setAccountEntity(toAccount);

            TransactionEntity creditTransaction = new TransactionEntity();
            creditTransaction.setAmount(amount);
            creditTransaction.setType("Credit");
            creditTransaction.setTimestamp(LocalDateTime.now());
            creditTransaction.setAccountEntity(toAccount);

            transactionRepository.save(debitTransaction);
            transactionRepository.save(creditTransaction);

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            // Apply interest if the toAccount is a Savings Account
            if (toAccount.getAccountType().equalsIgnoreCase("Savings")) {
                applyInterest(toAccount.getId());
            }
        } else {
            throw new IllegalArgumentException("Only Current Account can make payments");
        }
        
        notificationService.sendNotification("The found transefered from : "+
        		fromAccountId + " to : "+toAccountId + " amount : "+amount +
        		" successfully ");
    }

    public void applyInterest(Long accountId) {
        AccountEntity account = accountRepository.findById(accountId).orElseThrow();
        if (account.getAccountType().equalsIgnoreCase("Savings")) {
            double interest = account.getBalance() * 0.005;
            account.setBalance(account.getBalance() + interest);

            TransactionEntity interestTransaction = new TransactionEntity();
            interestTransaction.setAmount(interest);
            interestTransaction.setType("Credit");
            interestTransaction.setTimestamp(LocalDateTime.now());
            interestTransaction.setAccountEntity(account);

            transactionRepository.save(interestTransaction);
            accountRepository.save(account);
        }
    }

    public void chargeFee(Long accountId, double amount) {
    	AccountEntity account = accountRepository.findById(accountId).orElseThrow();
        double fee = amount * 0.0005;
        account.setBalance(account.getBalance() - fee);

        TransactionEntity feeTransaction = new TransactionEntity();
        feeTransaction.setAmount(fee);
        feeTransaction.setType("Debit");
        feeTransaction.setTimestamp(LocalDateTime.now());
        feeTransaction.setAccountEntity(account);

        transactionRepository.save(feeTransaction);
        accountRepository.save(account);
    }
}
