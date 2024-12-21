package com.bankX.bank.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    private String type; // "Transfer", "Payment", "Deposit"
    private double amount;
    private String description;

    @ManyToOne
    @JsonManagedReference
    private AccountEntity accountEntity;
    
    

	public TransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public TransactionEntity(LocalDateTime timestamp, String type, double amount, String description,
			AccountEntity accountEntity) {
		super();
		this.timestamp = timestamp;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.accountEntity = accountEntity;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}
    
    
}
