package com.example.phonestudentproject.mapper;

import com.example.phonestudentproject.model.DTO.balance.BalanceOperationDTO;
import com.example.phonestudentproject.model.entity.balance.BalanceOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface BalanceOperationMapper {

    @Mapping(target = "operationDate", source = "entity.operationDate")
    @Mapping(target = "modifyDate", ignore = true)
    BalanceOperationDTO toDto(BalanceOperation entity);

    @Mapping(target = "operationDate", source = "dto.operationDate")
    BalanceOperation toEntity(BalanceOperationDTO dto);

}
