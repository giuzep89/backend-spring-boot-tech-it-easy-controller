package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.CIModuleDto;
import com.novi.techiteasycontroller.dtos.CIModuleInputDto;
import com.novi.techiteasycontroller.exceptions.NameTooLongException;
import com.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import com.novi.techiteasycontroller.mappers.CIModuleMapper;
import com.novi.techiteasycontroller.models.CIModule;
import com.novi.techiteasycontroller.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public CIModuleDto createCIModule(CIModuleInputDto ciModuleInputDto) {
        if (ciModuleInputDto.name.length() > 20) {
            throw new NameTooLongException("The name you provided is too long");
        }
        CIModule newCIModule = CIModuleMapper.toEntity(ciModuleInputDto);

        this.ciModuleRepository.save(newCIModule);

        return CIModuleMapper.toDto(newCIModule);
    }

    public List<CIModuleDto> getAllCIModules() {
        List<CIModule> ciModules = this.ciModuleRepository.findAll();
        List<CIModuleDto> ciModuleDtos = new ArrayList<>();

        for (CIModule ciModule : ciModules) {
            ciModuleDtos.add(CIModuleMapper.toDto(ciModule));
        }

        return ciModuleDtos;
    }

    public CIModuleDto getCIModuleById(Long id) {
        CIModule ciModuleToFind = ciModuleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The CI Module with id " + id + " was not found."));
        return CIModuleMapper.toDto(ciModuleToFind);
    }

    public CIModuleDto updateCIModulePrice(Long id, CIModuleInputDto ciModuleInputDto) {
        CIModule ciModuleToUpdate = ciModuleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The CI Module with id " + id + " was not found."));

        ciModuleToUpdate.setPrice(ciModuleInputDto.price);
        ciModuleRepository.save(ciModuleToUpdate);

        return CIModuleMapper.toDto(ciModuleToUpdate);
    }

    public void deleteCIModuleById(Long id) {
        CIModule ciModuleToFind = ciModuleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The CI Module with id " + id + " was not found."));

        ciModuleRepository.delete(ciModuleToFind);
    }
}
