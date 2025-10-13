package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.dtos.CIModuleDto;
import com.novi.techiteasycontroller.dtos.CIModuleInputDto;
import com.novi.techiteasycontroller.services.CIModuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class CIModuleController {

    private CIModuleService CIModuleService;

    public CIModuleController(CIModuleService CIModuleService) {
        this.CIModuleService = CIModuleService;
    }

    @GetMapping
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        return ResponseEntity.ok(CIModuleService.getAllCIModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(CIModuleService.getCIModuleById(id));
    }

    @PostMapping
    public ResponseEntity<CIModuleDto> addCIModule(@Valid @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto newCIModule = CIModuleService.createCIModule(ciModuleInputDto);

        URI location = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCIModule.id)
                .toUriString());

        return ResponseEntity.created(location).body(newCIModule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModulePrice(@Valid @PathVariable Long id, @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto dto = CIModuleService.updateCIModulePrice(id, ciModuleInputDto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CIModuleDto> deleteCIModule(@Valid @PathVariable Long id) {
        CIModuleService.deleteCIModuleById(id);

        return ResponseEntity.noContent().build();
    }

}
