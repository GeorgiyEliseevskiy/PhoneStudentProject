package com.example.phonestudentproject.service.api.Balance;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;

import java.math.BigDecimal;

/**
 * Интерфейс сервиса для работы с Балансом.
 */
public interface BalanceService {

    /**
     * Посчитать баланс после звонка.
     *
     * @param phoneDTO - {@link PhoneDTO}
     * @param duration - длительность звонка.
     * @return - Баланас.
     */
    @Deprecated
    BigDecimal calculateBalanceAfterCall(PhoneDTO phoneDTO, String duration);

    /**
     * Получить баланс по номеру телефона.
     *
     * @param phoneNumber - номер телефона.
     * @return - {@link BalanceDTO}
     */
    BalanceDTO getBalanceFromPhoneNumber(String phoneNumber);

    /**
     * Пополнить баланс.
     * Если телефон находиться в заблокированном состояние, то при внесение средств (баланс > 0) статус изменяется.
     *
     * @param sum - сумма
     * @param phoneNumber - номер телефона
     */
    void depositBalance(String sum, String phoneNumber);
}
