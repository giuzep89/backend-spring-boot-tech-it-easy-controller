package com.novi.techiteasycontroller.mappers;

import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.dtos.TelevisionInputDto;
import com.novi.techiteasycontroller.models.Television;

public class TelevisionMapper {

    public static Television toEntity(TelevisionInputDto inputDto){
        Television entity = new Television();

        entity.setType(inputDto.type);
        entity.setBrand(inputDto.brand);
        entity.setName(inputDto.name);
        entity.setPrice(inputDto.price);
        entity.setAvailableSizes(inputDto.availableSizes);
        entity.setRefreshRate(inputDto.refreshRate);
        entity.setScreenQuality(inputDto.screenQuality);
        entity.setScreenType(inputDto.screenType);
        entity.setSmartTv(inputDto.smartTv);
        entity.setWifi(inputDto.wifi);
        entity.setBluetooth(inputDto.bluetooth);
        entity.setHdr(inputDto.hdr);
        entity.setAmbiLight(inputDto.ambiLight);
        entity.setVoiceControl(inputDto.voiceControl);

        return entity;
    }

    public static TelevisionDto toDto(Television entity){
        TelevisionDto dto = new TelevisionDto();

        dto.id = entity.getId();
        dto.type = entity.getType();
        dto.brand = entity.getBrand();
        dto.name = entity.getName();
        dto.price = entity.getPrice();
        dto.availableSizes = entity.getAvailableSizes();
        dto.refreshRate = entity.getRefreshRate();
        dto.screenQuality = entity.getScreenQuality();
        dto.screenType = entity.getScreenType();
        dto.smartTv = entity.hasSmartTv();
        dto.wifi = entity.hasWifi();
        dto.bluetooth = entity.hasBluetooth();
        dto.ambiLight = entity.hasAmbiLight();
        dto.voiceControl = entity.hasVoiceControl();

        return dto;
    }
}

