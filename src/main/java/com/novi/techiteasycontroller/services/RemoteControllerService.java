package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.RemoteControllerDto;
import com.novi.techiteasycontroller.dtos.RemoteControllerInputDto;
import com.novi.techiteasycontroller.models.RemoteController;
import com.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    // TODO: Write logic (methods)

//    public RemoteControllerDto addRemoteController(RemoteControllerInputDto remoteControllerInputDto) {
//        RemoteController remoteController = Remote
//    }





}
