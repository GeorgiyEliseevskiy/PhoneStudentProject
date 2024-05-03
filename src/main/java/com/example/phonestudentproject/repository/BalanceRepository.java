package com.example.phonestudentproject.repository;

import com.example.phonestudentproject.model.entity.balance.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query("SELECT b FROM Balance b " +
            "WHERE b.phoneNumber = :phoneNumber")
    Optional<Balance> findBalanceByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Balance b " +
            "SET b.balance =:balance " +
            "WHERE b.phoneNumber =:phoneNumber")
    void updateSumByPhoneNumber(@Param("phoneNumber") String phoneNumber, @Param("balance") BigDecimal balance);

    @Query("SELECT b.id " +
            "FROM Balance b " +
            "WHERE b.phoneNumber =:phoneNumber")
    Long findBalanceIdByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
