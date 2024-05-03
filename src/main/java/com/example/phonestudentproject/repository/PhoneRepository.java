package com.example.phonestudentproject.repository;

import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью телефон.
 */
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    /**
     * Найти телефон по номеру телефона.
     *
     * @param phoneNumber - номер телефона
     * @return {@link Phone}
     */
    Optional<Phone> findPhoneByPhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Phone AS p " +
            "SET p.status =:status " +
            "WHERE p.id =:id")
    void updatePhoneStatus(@Param("status") PhoneStatusEnum status, @Param("id") Long id);

    @Query("SELECT p.id " +
            "FROM Phone p " +
            "WHERE p.phoneNumber =:phoneNumber")
    Optional<Long> findPhoneIdByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
