package com.novi.techiteasycontroller.mappers;

import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.dtos.TelevisionInputDto;
import com.novi.techiteasycontroller.models.Television;

public class TelevisionMapper {

    public static Television toEntity(TelevisionInputDto televisionInputDto){
        Television television = new Television();

        television.setType(televisionInputDto.type);
        television.setBrand(televisionInputDto.brand);
        television.setName(televisionInputDto.name);
        television.setPrice(televisionInputDto.price);
        television.setAvailableSizes(televisionInputDto.availableSizes);
        television.setRefreshRate(televisionInputDto.refreshRate);
        television.setScreenQuality(televisionInputDto.screenQuality);
        television.setScreenType(televisionInputDto.screenType);
        television.setSmartTv(televisionInputDto.smartTv);
        television.setWifi(televisionInputDto.wifi);
        television.setBluetooth(televisionInputDto.bluetooth);
        television.setHdr(televisionInputDto.hdr);
        television.setAmbiLight(televisionInputDto.ambiLight);
        television.setVoiceControl(televisionInputDto.voiceControl);

        return television;
    }

    public static TelevisionDto toDto(Television television){
        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.id = television.getId();
        televisionDto.type = television.getType();
        televisionDto.brand = television.getBrand();
        televisionDto.name = television.getName();
        televisionDto.price = television.getPrice();
        televisionDto.availableSizes = television.getAvailableSizes();
        televisionDto.refreshRate = television.getRefreshRate();
        televisionDto.screenQuality = television.getScreenQuality();
        televisionDto.screenType = television.getScreenType();
        televisionDto.smartTv = television.hasSmartTv();
        televisionDto.wifi = television.hasWifi();
        televisionDto.bluetooth = television.hasBluetooth();
        televisionDto.ambiLight = television.hasAmbiLight();
        televisionDto.voiceControl = television.hasVoiceControl();

        return televisionDto;
    }
}

