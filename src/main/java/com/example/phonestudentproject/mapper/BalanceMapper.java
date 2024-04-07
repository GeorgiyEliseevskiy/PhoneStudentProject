package com.example.phonestudentproject.mapper;

import com.example.phonestudentproject.model.DTO.balance.BalanceOperationDTO;
import com.example.phonestudentproject.model.entity.balance.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PhoneMapper.class})
public interface BalanceMapper {

}
