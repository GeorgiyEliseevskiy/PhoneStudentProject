package com.example.phonestudentproject.service.impl.decorator;

import com.example.phonestudentproject.mapper.BalanceMapper;
import com.example.phonestudentproject.mapper.BalanceOperationMapper;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.model.DTO.balance.BalanceOperationDTO;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.balance.Balance;
import com.example.phonestudentproject.model.entity.balance.BalanceOperation;
import com.example.phonestudentproject.repository.BalanceOperationRepository;
import com.example.phonestudentproject.repository.BalanceRepository;
import com.example.phonestudentproject.repository.PhoneRepository;
import com.example.phonestudentproject.service.api.Balance.BalanceService;
import com.example.phonestudentproject.service.api.PhoneService;
import com.example.phonestudentproject.service.api.utils.PhoneServiceUtils;
import com.example.phonestudentproject.service.impl.Balance.BalanceServiceImpl;
import com.example.phonestudentproject.service.impl.PhoneServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceServiceDecorator implements BalanceService {

    private final BalanceServiceImpl balanceService;
    private final BalanceMapper balanceMapper;
    private final BalanceRepository balanceRepository;
    private final PhoneRepository phoneRepository;
    private final PhoneServiceUtils phoneService;
    private final BalanceOperationRepository balanceOperationRepository;
    private final BalanceOperationMapper balanceOperationMapper;

    @Override
    @Deprecated
    public BigDecimal calculateBalanceAfterCall(PhoneDTO phoneDTO, String duration) {
        return balanceService.calculateBalanceAfterCall(phoneDTO, duration);
    }

    @Override
    public BalanceDTO getBalanceFromPhoneNumber(String phoneNumber) {
        return balanceService.getBalanceFromPhoneNumber(phoneNumber);
    }

    public Double getBalanceNumByPhoneNumber(String phoneNumber) {
        BalanceDTO balanceFromPhoneNumber = getBalanceFromPhoneNumber(phoneNumber);
        return balanceFromPhoneNumber.getBalance().doubleValue();
    }

    public BalanceDTO deductMoneyToCall(String phoneNumber, String duration) {

        BalanceDTO balanceFromPhoneNumber = getBalanceFromPhoneNumber(phoneNumber);

        double balancePhoneFrom = balanceFromPhoneNumber.getBalance().doubleValue();
        double balanceAfterCall = balancePhoneFrom - Double.parseDouble(duration);

        checkNegativeBalance(phoneNumber, balanceAfterCall);

        String operationDateMessage = String.format("Текущий баланс абонента: %s - %s", phoneNumber,
                balancePhoneFrom - Integer.parseInt(duration));

        balanceRepository.updateSumByPhoneNumber(phoneNumber, BigDecimal.valueOf(
                balancePhoneFrom - Integer.parseInt(duration)));

        log.info(operationDateMessage);

        Optional<Balance> balanceByPhoneNumber = balanceRepository.findBalanceByPhoneNumber(phoneNumber);
        BalanceOperation balanceOperation = new BalanceOperation();
        balanceByPhoneNumber.ifPresent(balanceOperation::setBalance);
        balanceOperation.setOperationDate(operationDateMessage);
        balanceOperationRepository.save(balanceOperation);

        BalanceOperationDTO balanceOperationDTO = balanceOperationMapper.toDto(balanceOperation);
        balanceOperationDTO.setModifyDate(OffsetDateTime.now());

        balanceFromPhoneNumber.getHistoryOperation().add(balanceOperationDTO);
        balanceFromPhoneNumber.setBalance(BigDecimal.valueOf(balanceAfterCall));

        return balanceFromPhoneNumber;
    }

    private void checkNegativeBalance(String phoneNumber, double balanceAfterCall) {
        if (balanceAfterCall <= 0) {
            phoneRepository.findPhoneIdByPhoneNumber(phoneNumber)
                            .ifPresent(id -> phoneService.setPhoneStatusRuntime(PhoneStatusEnum.BLOCKED, id));

            log.info(String.format("Абонент %s заблокирован с отрицательным балансом %s", phoneNumber, balanceAfterCall));

        }
    }

    @Override
    public void depositBalance(String sum, String phoneNumber) {
        balanceService.depositBalance(sum, phoneNumber);
    }

    public Balance createBalance(BalanceDTO balanceDTO) {
        Balance entity = balanceMapper.toEntity(balanceDTO);
        return balanceRepository.save(entity);
    }
}

