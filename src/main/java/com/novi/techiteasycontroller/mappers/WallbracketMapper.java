package com.novi.techiteasycontroller.mappers;

import com.novi.techiteasycontroller.dtos.WallbracketDto;
import com.novi.techiteasycontroller.models.Wallbracket;

public class WallbracketMapper {

    public static Wallbracket toEntity(WallbracketDto dto){
        Wallbracket entity = new Wallbracket();

        entity.setSize(dto.size);
        entity.setName(dto.name);
        entity.setPrice(dto.price);
        entity.setAdjustable(dto.adjustable);

        return entity;
    }

    public static WallbracketDto toDto(Wallbracket entity){
        WallbracketDto dto = new WallbracketDto();

        dto.id = entity.getId();
        dto.name = entity.getName();
        dto.price = entity.getPrice();
        dto.adjustable = entity.isAdjustable();
        dto.size = entity.getSize();

        return dto;
    }


}
