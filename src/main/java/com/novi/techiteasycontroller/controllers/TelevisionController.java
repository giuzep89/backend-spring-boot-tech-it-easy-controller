package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.dtos.IdInputDto;
import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.dtos.TelevisionInputDto;
import com.novi.techiteasycontroller.models.Television;
import com.novi.techiteasycontroller.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevisionById(@PathVariable Long id) {
        return ResponseEntity.ok(televisionService.getTelevisionById(id));
    }


    @PostMapping
    public ResponseEntity<TelevisionDto> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto newTelevision = televisionService.createTelevision(televisionInputDto);

        URI location = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTelevision.id)
                .toUriString());

        return ResponseEntity.created(location).body(newTelevision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTelevisionPrice(@Valid @PathVariable Long id, @RequestBody TelevisionInputDto inputDto) {
        TelevisionDto dto = televisionService.updateTelevisionPrice(id, inputDto);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/remotecontroller")
    public ResponseEntity<TelevisionDto> addRemoteController(@Valid @PathVariable Long televisionId, @RequestBody IdInputDto remoteControllerId) {
        TelevisionDto dto = televisionService.assignRemoteControllerToTelevision(televisionId, remoteControllerId.id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/cimodule")
    public ResponseEntity<TelevisionDto> addCiModule (@Valid @PathVariable Long televisionId, @RequestBody IdInputDto ciModuleId) {
        TelevisionDto dto = televisionService.assignCIModuleToTelevision(televisionId, ciModuleId.id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/wallbracket")
    public ResponseEntity<TelevisionDto> addWallbracket (@Valid @PathVariable Long televisionId, @RequestBody IdInputDto wallbracketId) {
        TelevisionDto dto = televisionService.assignWallbracketToTelevision(televisionId, wallbracketId.id);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevisionById(id);

        return ResponseEntity.noContent().build();
    }
}
