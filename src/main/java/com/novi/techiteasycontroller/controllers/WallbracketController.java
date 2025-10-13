package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.dtos.WallbracketDto;
import com.novi.techiteasycontroller.dtos.WallbracketInputDto;
import com.novi.techiteasycontroller.services.WallbracketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wallbrackets")
public class WallbracketController {

    private final WallbracketService wallbracketService;

    public WallbracketController(WallbracketService wallbracketService) {
        this.wallbracketService = wallbracketService;
    }

    @GetMapping
    public ResponseEntity<List<WallbracketDto>> getAllWallbrackets() {
        return ResponseEntity.ok(wallbracketService.getAllWallbrackets());
    }

    @GetMapping
    public ResponseEntity<WallbracketDto> getWallbracketById(@RequestParam Long id){
        return ResponseEntity.ok(wallbracketService.getWallbracketById(id));
    }

    @PostMapping
    public ResponseEntity<WallbracketDto> createWallbracket(@Valid @RequestBody WallbracketInputDto wallbracketInputDto){
        WallbracketDto newWallbracket = wallbracketService.addWallbracket(wallbracketInputDto);

        URI location = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newWallbracket.id)
                .toUriString());

        return ResponseEntity.created(location).body(newWallbracket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallbracketDto> updateWallbracketPrice(@Valid @PathVariable Long id, WallbracketInputDto wallbracketInputDto){
        WallbracketDto dto = wallbracketService.updateWallbracketPrice(id, wallbracketInputDto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WallbracketDto> deleteWallbracket(@PathVariable Long id){
        wallbracketService.deleteWallbracketById(id);

        return ResponseEntity.noContent().build();
    }


}
