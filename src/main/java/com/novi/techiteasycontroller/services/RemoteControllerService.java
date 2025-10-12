package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.RemoteControllerDto;
import com.novi.techiteasycontroller.dtos.RemoteControllerInputDto;
import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.mappers.RemoteControllerMapper;
import com.novi.techiteasycontroller.models.RemoteController;
import com.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }


    public RemoteControllerDto addRemoteController(RemoteControllerInputDto inputDto) {
        RemoteController newRemoteController = RemoteControllerMapper.toEntity(inputDto);

        this.remoteControllerRepository.save(newRemoteController);

        return RemoteControllerMapper.toDto(newRemoteController);
    }


    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteController> remoteControllers = this.remoteControllerRepository.findAll();
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remoteController : remoteControllers) {
            RemoteControllerDto remoteControllerDto = RemoteControllerMapper.toDto(remoteController);
            remoteControllerDtos.add(remoteControllerDto);
        }

        return remoteControllerDtos;
    }

    public RemoteControllerDto getRemoteControllerById(Long id) {
        RemoteController remoteToFind = remoteControllerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The Remote Controller with id " + id + " was not found."));
        return RemoteControllerMapper.toDto(remoteToFind);
    }

    public RemoteControllerDto updateRemoteControllerPrice(Long id, RemoteControllerInputDto inputDto) {
        RemoteController remoteToUpdate = remoteControllerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The Remote Controller with id " + id + " was not found."));

        remoteToUpdate.setPrice(inputDto.price);
        remoteControllerRepository.save(remoteToUpdate);

        return RemoteControllerMapper.toDto(remoteToUpdate);
    }

    public void deleteRemoteControllerById(Long id) {
        RemoteController remoteToFind = remoteControllerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The Remote Controller with id " + id + " was not found."));

        remoteControllerRepository.delete(remoteToFind);
    }
}
