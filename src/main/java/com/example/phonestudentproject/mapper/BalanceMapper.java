package com.example.phonestudentproject.mapper;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.model.entity.balance.Balance;
import com.example.phonestudentproject.model.entity.balance.BalanceOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PhoneMapper.class, BalanceOperationMapper.class})
public interface BalanceMapper {

    @Mapping(source = "balance", target = "balance")
    @Mapping(target = "historyOperation", ignore = true)
    Balance toEntity(BalanceDTO balanceDTO);

    @Mapping(source = "balance.balance", target = "balance")
    @Mapping(source = "historyOperation", target = "historyOperation")
    BalanceDTO toDto(Balance balance);
}
