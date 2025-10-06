package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.dtos.TelevisionInputDto;
import com.novi.techiteasycontroller.exceptions.NameTooLongException;
import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.mappers.TelevisionMapper;
import com.novi.techiteasycontroller.models.Television;
import com.novi.techiteasycontroller.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTVs(){
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getOneTV(@PathVariable Long id) {
        Optional<TelevisionDto> foundTV = TelevisionMapper.toDto(televisionService.getTelevisionById(id));

        if (foundTV.isPresent()) {
            return ResponseEntity.ok(foundTV.get());
        } else {
            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
        }
    }


    @PostMapping
    public ResponseEntity<TelevisionDto> addTV(@RequestBody TelevisionInputDto televisionInputDto) {
        if(televisionInputDto.getBrand().length() > 20){
            throw new NameTooLongException("The brand name you provided is too long");
        } else {
            TelevisionDto televisionDto = this.televisionService.addTelevision(televisionInputDto);
            return ResponseEntity.created(null).body(televisionDto);
        }
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Television> updateTVPrice(@PathVariable Long id, @RequestBody Television tvWithNewPrice){
//        Optional<Television> possibleTV = televisionRepository.findById(id);
//
//        if(possibleTV.isPresent()){
//            Television tvToUpdate = possibleTV.get();
//            tvToUpdate.setPrice(tvWithNewPrice.getPrice());
//            televisionRepository.save(tvToUpdate);
//            return ResponseEntity.ok(tvToUpdate);
//        } else {
//            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
//        }
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Television> deleteTV(@PathVariable Long id) {
//        Television tvToDelete = televisionRepository.findById(id)
//                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + id + " was not found."));
//        televisionRepository.delete(tvToDelete);
//        return ResponseEntity.noContent().build();
//    }

}
