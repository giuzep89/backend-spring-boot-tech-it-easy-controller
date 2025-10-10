package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteControllerService {


    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    // Continue writing the CRUD methods after having the DTOs ready





}
