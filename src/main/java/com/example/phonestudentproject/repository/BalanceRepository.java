package com.example.phonestudentproject.repository;

import com.example.phonestudentproject.model.entity.balance.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query("SELECT b FROM Balance b " +
            "JOIN b.phone p " +
            "WHERE p.phoneNumber = :phoneNumber")
    Optional<Balance> findBalanceByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
