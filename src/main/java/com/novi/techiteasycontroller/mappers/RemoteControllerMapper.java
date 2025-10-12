package com.novi.techiteasycontroller.mappers;

import com.novi.techiteasycontroller.dtos.RemoteControllerDto;
import com.novi.techiteasycontroller.dtos.RemoteControllerInputDto;
import com.novi.techiteasycontroller.models.RemoteController;

public class RemoteControllerMapper {

    public static RemoteController toEntity(RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController remoteController = new RemoteController();

        remoteController.setBrand(remoteControllerInputDto.brand);
        remoteController.setPrice(remoteControllerInputDto.price);
        remoteController.setCompatibleWith(remoteControllerInputDto.compatibleWith);
        remoteController.setPrice(remoteControllerInputDto.price);
        remoteController.setBatteryType(remoteControllerInputDto.batteryType);

        return remoteController;
    }

    public static RemoteControllerDto toDto(RemoteController remoteController) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();

        remoteControllerDto.id = remoteController.getId();
        remoteControllerDto.name = remoteController.getName();
        remoteControllerDto.brand = remoteController.getBrand();
        remoteControllerDto.price = remoteController.getPrice();
        remoteControllerDto.compatibleWith = remoteController.getCompatibleWith();
        remoteControllerDto.batteryType = remoteController.getBatteryType();

        return remoteControllerDto;
    }
}