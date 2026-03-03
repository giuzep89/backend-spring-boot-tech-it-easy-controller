package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.TelevisionDto;
import com.novi.techiteasycontroller.dtos.TelevisionInputDto;
import com.novi.techiteasycontroller.exceptions.NameTooLongException;
import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.mappers.TelevisionMapper;
import com.novi.techiteasycontroller.models.CIModule;
import com.novi.techiteasycontroller.models.RemoteController;
import com.novi.techiteasycontroller.models.Television;
import com.novi.techiteasycontroller.models.Wallbracket;
import com.novi.techiteasycontroller.repositories.CIModuleRepository;
import com.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import com.novi.techiteasycontroller.repositories.TelevisionRepository;
import com.novi.techiteasycontroller.repositories.WallbracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final CIModuleRepository ciModuleRepository;
    private final WallbracketRepository wallbracketRepository;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, CIModuleRepository ciModuleRepository, WallbracketRepository wallbracketRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
        this.wallbracketRepository = wallbracketRepository;
    }

    public TelevisionDto createTelevision(TelevisionInputDto televisionInputDto) {
        Television newTelevision = TelevisionMapper.toEntity(televisionInputDto);

        this.televisionRepository.save(newTelevision);

        return TelevisionMapper.toDto(newTelevision);
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> televisions = this.televisionRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionDto dto = TelevisionMapper.toDto(television);
            televisionDtos.add(dto);
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

    public TelevisionDto assignRemoteControllerToTelevision(Long televisionId, Long remoteControllerId) {
        Television television = televisionRepository.findById(televisionId)
                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + televisionId + " was not found."));

        RemoteController remoteController = remoteControllerRepository.findById(remoteControllerId)
                .orElseThrow(() -> new RecordNotFoundException("The RemoteController with id " + remoteControllerId + " was not found."));

        television.setRemoteController(remoteController);

        televisionRepository.save(television);

        return TelevisionMapper.toDto(television);
    }

    public TelevisionDto assignCIModuleToTelevision(Long televisionId, Long CIModuleId) {
        Television television = televisionRepository.findById(televisionId)
                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + televisionId + " was not found."));

        CIModule ciModule = ciModuleRepository.findById(CIModuleId)
                .orElseThrow(() -> new RecordNotFoundException("The CI module with id " + CIModuleId + " was not found."));

        List<CIModule> ciModules = television.getCiModules();

        ciModules.add(ciModule);

        television.setCiModules(ciModules);

        televisionRepository.save(television);

        return  TelevisionMapper.toDto(television);
    }

    public TelevisionDto assignWallbracketToTelevision(Long televisionId, Long wallbracketId) {
        Television television = televisionRepository.findById(televisionId)
                .orElseThrow(() -> new RecordNotFoundException("The TV with id " + televisionId + " was not found."));

        Wallbracket wallbracket = wallbracketRepository.findById(wallbracketId).orElseThrow(() -> new RecordNotFoundException("Wallbracket with id " + wallbracketId + " was not found."));

        List<Wallbracket> wallbracketsToAssign = television.getWallbrackets();

        wallbracketsToAssign.add(wallbracket);

        television.setWallbrackets(wallbracketsToAssign);

        televisionRepository.save(television);

        return TelevisionMapper.toDto(television);
    }



}
