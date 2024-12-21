package com.bankX.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankX.bank.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
