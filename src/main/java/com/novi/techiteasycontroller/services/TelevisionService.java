package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.dtos.TelevisionInputDto;
import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.mappers.TelevisionMapper;
import com.novi.techiteasycontroller.models.Television;
import com.novi.techiteasycontroller.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {


    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }

    // TODO Add service functions:

    public TelevisionDto addTelevision(TelevisionInputDto televisionInputDto){
        Television television = TelevisionMapper.toEntity(televisionInputDto);

        this.televisionRepository.save(television);

        return TelevisionMapper.toDto(television);
    }


    public List<TelevisionDto> getAllTelevisions(){
        List<Television> televisions = this.televisionRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions){
            TelevisionMapper.toDto(television);
            televisionDtos.add(TelevisionMapper.toDto(television));
        }

        return  televisionDtos;
    }

    // een functie voor het ophalen van 1 Television

    public TelevisionDto getTelevisionById(Long id){
        Television tvToFind = televisionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + id + " was not found."));
        return TelevisionMapper.toDto(tvToFind);
    }


    // een functie voor het verwijderen van 1 Television




    // een functie voor het updaten van 1 Television



}
