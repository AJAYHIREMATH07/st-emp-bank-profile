package com.bankX.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankX.bank.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
