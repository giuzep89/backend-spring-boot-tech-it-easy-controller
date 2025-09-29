package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.exceptions.NameTooLongException;
import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.models.Television;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.novi.techiteasycontroller.repositories.TelevisionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Television>> getAllTVs(){
        return ResponseEntity.ok(televisionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getOneTV(@PathVariable Long id) {
        Optional<Television> foundTV = televisionRepository.findById(id);

        if (foundTV.isPresent()) {
            return ResponseEntity.ok(foundTV.get());
        } else {
            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
        }
    }

    @PostMapping
    public ResponseEntity<Television> addTV(@RequestBody Television tv){
        if(tv.getBrand().length() > 20){
            throw new NameTooLongException("The brand name you provided is too long");
        } else {
            this.televisionRepository.save(tv);
            return ResponseEntity.created(null).body(tv);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTVPrice(@PathVariable Long id, @RequestBody Television tvWithNewPrice){
        Optional<Television> possibleTV = televisionRepository.findById(id);

        if(possibleTV.isPresent()){
            Television tvToUpdate = possibleTV.get();
            tvToUpdate.setPrice(tvWithNewPrice.getPrice());
            televisionRepository.save(tvToUpdate);
            return ResponseEntity.ok(tvToUpdate);
        } else {
            throw new RecordNotFoundException("The TV with id " + id + " was not found.");
        }
    }

    // In this one I wanted to experiment with using the .orElseThrow(() method instead of using
    // an if-else statement. It's much neater this way!
    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTV(@PathVariable Long id) {
        Television tvToDelete = televisionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + id + " was not found."));
        televisionRepository.delete(tvToDelete);
        return ResponseEntity.noContent().build();
    }

}
