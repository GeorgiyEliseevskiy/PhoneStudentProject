package com.example.phonestudentproject.repository;

import com.example.phonestudentproject.model.entity.balance.BalanceOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceOperationRepository extends JpaRepository<BalanceOperation, Long> {

}
