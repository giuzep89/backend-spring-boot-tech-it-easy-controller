package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.dtos.TelevisionInputDto;
import com.novi.techiteasycontroller.models.Television;
import com.novi.techiteasycontroller.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTVs() {
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getOneTV(@PathVariable Long id) {
        TelevisionDto tvToFind = televisionService.getTelevisionById(id);

        return ResponseEntity.ok(tvToFind);
    }


    @PostMapping
    public ResponseEntity<TelevisionDto> addTV(@Valid @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto newTelevision = televisionService.addTelevision(televisionInputDto);

        return ResponseEntity.created(null).body(newTelevision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTVPrice(@Valid @PathVariable Long id, @RequestBody TelevisionInputDto tvToUpdate) {
        TelevisionDto updatedTv = televisionService.updateTelevisionPrice(id, tvToUpdate);

        return ResponseEntity.ok(updatedTv);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTV(@PathVariable Long id) {
        televisionService.deleteTelevisionById(id);

        return ResponseEntity.noContent().build();
    }

}
