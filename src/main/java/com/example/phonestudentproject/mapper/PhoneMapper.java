package com.example.phonestudentproject.mapper;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.balance.BalanceOperationDTO;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.model.entity.balance.Balance;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BalanceMapper.class})
public interface PhoneMapper {

    Phone toEntity(PhoneDTO phoneDTO);

    PhoneDTO toDto(Phone phone);

}
