package com.bankX.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bankX.bank.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>,
        JpaSpecificationExecutor<CustomerEntity>{

}
