package com.example.phonestudentproject.repository;

import com.example.phonestudentproject.model.entity.balance.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
