package com.example.phonestudentproject.repository;

import com.example.phonestudentproject.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findPhoneByPhoneNumber(String phoneNumber);

}
