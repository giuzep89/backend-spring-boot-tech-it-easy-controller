package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.dtos.TelevisionInputDto;
import com.novi.techiteasycontroller.exceptions.NameTooLongException;
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

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public TelevisionDto addTelevision(TelevisionInputDto televisionInputDto) {
        if (televisionInputDto.brand.length() > 20) {
            throw new NameTooLongException("The brand name you provided is too long");
        }
        Television television = TelevisionMapper.toEntity(televisionInputDto);

        this.televisionRepository.save(television);

        return TelevisionMapper.toDto(television);

    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> televisions = this.televisionRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionMapper.toDto(television);
            televisionDtos.add(TelevisionMapper.toDto(television));
        }

        return televisionDtos;
    }

    public TelevisionDto getTelevisionById(Long id) {
        Television tvToFind = televisionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + id + " was not found."));
        return TelevisionMapper.toDto(tvToFind);
    }

    public TelevisionDto updateTelevisionPrice(Long id, TelevisionInputDto televisionInputDto) {
        Television tvToUpdate = televisionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + id + " was not found."));

        tvToUpdate.setPrice(televisionInputDto.price);
        televisionRepository.save(tvToUpdate);

        return TelevisionMapper.toDto(tvToUpdate);
    }

    public void deleteTelevisionById(Long id) {
        Television tvToFind = televisionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + id + " was not found."));

        televisionRepository.delete(tvToFind);
    }


}
