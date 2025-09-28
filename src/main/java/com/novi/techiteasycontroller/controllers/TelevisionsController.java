package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.exceptions.NameTooLongException;
import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.models.Television;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.novi.techiteasycontroller.repositories.TelevisionRepository;

import java.util.Map;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }

    //TODO: Replace fake database with TelevisionRepository!!

    @GetMapping
    public ResponseEntity<Map<Integer, Television>> getAllTVs(){
        return ResponseEntity.ok(this.databaseOfTVs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getOneTV(@PathVariable int id) {
        if (databaseOfTVs.containsKey(id)) {
            Television tv = databaseOfTVs.get(id);
            return ResponseEntity.ok(tv);
        } else {
            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
        }
    }

    @PostMapping
    public ResponseEntity<String> addTV(@RequestBody Television tv){
        if(tv.getBrand().length() > 20){
            throw new NameTooLongException("The brand name you provided is too long");
        } else {
            this.databaseOfTVs.put(tv.getId(), tv);
            return ResponseEntity.created(null).body("television");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTVPrice(@PathVariable int id, @RequestBody Television updatedTV){
        if(databaseOfTVs.containsKey(id)){
            Television foundTV = databaseOfTVs.get(id);
            foundTV.setPrice(updatedTV.getPrice());
            return ResponseEntity.noContent().build();
        } else {
            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTV(@PathVariable int id){
        if(!databaseOfTVs.containsKey(id)){
            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
        } else {
            databaseOfTVs.remove(id);
            return ResponseEntity.noContent().build();
        }
    }
}
