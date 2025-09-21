package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.models.Television;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class TelevisionsController {
    Map<Integer, Television> databaseOfTVs = new HashMap<Integer,Television>();

    @GetMapping("/tvs")
    public ResponseEntity<Map<Integer, Television>> getAllTVs(){
        return ResponseEntity.ok(this.databaseOfTVs);
    }

    @GetMapping("/tvs/{id}")
    public ResponseEntity<String> getOneTV(@PathVariable int id) {
        if (databaseOfTVs.containsKey(id)) {
            Television tv = databaseOfTVs.get(id);
            return ResponseEntity.ok("television");
        } else {
            throw new RuntimeException("The TV with id " + id + " was not found.");
        }
    }

    @PostMapping
    public ResponseEntity<String> addTV(@RequestBody Television tv, int id){
        this.databaseOfTVs.put(id, tv);
        return ResponseEntity.created(null).body("television");
    }

    @PutMapping("/tvs/{id}")
    public ResponseEntity<String> updateTVPrice(@PathVariable int id, @RequestBody Television updatedTV){
        if(databaseOfTVs.containsKey(id)){
            Television foundTV = databaseOfTVs.get(id);
            foundTV.setPrice(updatedTV.getPrice());
            return ResponseEntity.noContent().build();
        } else {
            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
        }
    }

    @DeleteMapping("/tvs/{id}")
    public ResponseEntity<String> deleteTV(@PathVariable int id){
        if(!databaseOfTVs.containsKey(id)){
            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
        } else {
            Television foundTV = databaseOfTVs.get(id);
            databaseOfTVs.remove(foundTV);
            return ResponseEntity.noContent().build();
        }
    }




}
