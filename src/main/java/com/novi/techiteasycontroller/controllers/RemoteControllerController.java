package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.dtos.RemoteControllerDto;
import com.novi.techiteasycontroller.dtos.RemoteControllerInputDto;
import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.services.RemoteControllerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/remote_controllers")
public class RemoteControllerController {

    private RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {
        return ResponseEntity.ok(remoteControllerService.getAllRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteControllerById(Long id) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.getRemoteControllerById(id);

        return ResponseEntity.ok(remoteControllerDto);
    }

    @PostMapping
    public ResponseEntity<RemoteControllerDto> addRemoteController(@Valid @RequestBody RemoteControllerInputDto inputDto) {
        RemoteControllerDto newRemote = remoteControllerService.addRemoteController(inputDto);

        URI location = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRemote.id)
                .toUriString());

        return ResponseEntity.created(null).body(newRemote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteControllerPrice(@Valid Long id, @RequestBody RemoteControllerInputDto inputDto) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.updateRemoteControllerPrice(id, inputDto);

        return ResponseEntity.ok(remoteControllerDto);
    }

    public ResponseEntity<RemoteControllerDto> deleteRemoteController(Long id) {
        remoteControllerService.deleteRemoteControllerById(id);

        return ResponseEntity.noContent().build();
    }
}
