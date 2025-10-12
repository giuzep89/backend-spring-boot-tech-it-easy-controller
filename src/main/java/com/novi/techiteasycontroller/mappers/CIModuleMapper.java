package com.novi.techiteasycontroller.mappers;

import com.novi.techiteasycontroller.dtos.CIModuleDto;
import com.novi.techiteasycontroller.dtos.CIModuleInputDto;
import com.novi.techiteasycontroller.models.CIModule;

public class CIModuleMapper {


    public static CIModule toEntity(CIModuleInputDto inputDto) {
        CIModule entity = new CIModule();

        entity.setName(inputDto.name);
        entity.setType(inputDto.type);
        entity.setPrice(inputDto.price);

        return entity;
    }


    public static CIModuleDto toDto(CIModule entity) {
        CIModuleDto dto = new CIModuleDto();

        dto.id = entity.getId();
        dto.name = entity.getName();
        dto.type = entity.getType();
        dto.price = entity.getPrice();

        return dto;
    }
}

