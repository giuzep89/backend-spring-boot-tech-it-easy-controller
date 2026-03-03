package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.WallbracketDto;
import com.novi.techiteasycontroller.dtos.WallbracketInputDto;
import com.novi.techiteasycontroller.exceptions.NameTooLongException;
import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.mappers.WallbracketMapper;
import com.novi.techiteasycontroller.models.Wallbracket;
import com.novi.techiteasycontroller.repositories.WallbracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WallbracketService {

    private final WallbracketRepository wallbracketRepository;

    public WallbracketService(WallbracketRepository wallbracketRepository) {
        this.wallbracketRepository = wallbracketRepository;
    }

    public WallbracketDto createWallbracket(WallbracketInputDto wallbracketInputDto) {
        if (wallbracketInputDto.name.length() > 20) {
            throw new NameTooLongException("The name you provided is too long");
        }
        Wallbracket newWallbracket = WallbracketMapper.toEntity(wallbracketInputDto);

        this.wallbracketRepository.save(newWallbracket);

        return WallbracketMapper.toDto(newWallbracket);
    }

    public List<WallbracketDto> getAllWallbrackets() {
        List<Wallbracket> wallbrackets = this.wallbracketRepository.findAll();
        List<WallbracketDto> wallbracketDtos = new ArrayList<>();

        for (Wallbracket wallbracket : wallbrackets) {
            WallbracketMapper.toDto(wallbracket);
            wallbracketDtos.add(WallbracketMapper.toDto(wallbracket));
        }

        return wallbracketDtos;
    }

    public WallbracketDto getWallbracketById(Long id) {
        Wallbracket wallbracketToFind = wallbracketRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The Wallbracket with id " + id + " was not found."));
        return WallbracketMapper.toDto(wallbracketToFind);
    }

    public WallbracketDto updateWallbracketPrice(Long id, WallbracketInputDto wallbracketInputDto) {
        Wallbracket wallbracketToUpdate = wallbracketRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The Wallbracket with id " + id + " was not found."));

        wallbracketToUpdate.setPrice(wallbracketInputDto.price);
        wallbracketRepository.save(wallbracketToUpdate);

        return WallbracketMapper.toDto(wallbracketToUpdate);
    }

    public void deleteWallbracketById(Long id) {
        Wallbracket wallbracketToFind = wallbracketRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The Wallbracket with id " + id + " was not found."));

        wallbracketRepository.delete(wallbracketToFind);
    }
}