package com.example.phonestudentproject.mapper;

import com.example.phonestudentproject.model.DTO.balance.BalanceOperationDTO;
import com.example.phonestudentproject.model.entity.balance.BalanceOperation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceOperationMapper {

    BalanceOperationDTO toDto(BalanceOperation entity);

    BalanceOperation toEntity(BalanceOperationDTO dto);
}
