package com.novi.techiteasycontroller.controllers;

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
public class TelevisionsController {

    private TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevisionById(id);

        return ResponseEntity.noContent().build();
    }
}
