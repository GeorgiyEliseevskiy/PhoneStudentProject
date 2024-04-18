package com.example.phonestudentproject.mapper;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.model.entity.balance.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PhoneMapper.class})
public interface BalanceMapper {

    Balance toEntity(BalanceDTO balanceDTO);

    BalanceDTO toDto(Balance balance);
}
