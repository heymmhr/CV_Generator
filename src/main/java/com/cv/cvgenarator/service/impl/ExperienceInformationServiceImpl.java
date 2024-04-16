package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.EducationInformation;
import com.cv.cvgenarator.entity.ExperienceInformation;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.BasicInformationRepo;
import com.cv.cvgenarator.repo.ExperienceInformationRepo;
import com.cv.cvgenarator.service.ExperienceInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ExperienceInformationServiceImpl implements ExperienceInformationService {

    private final ExperienceInformationRepo experienceInformationRepo;
    private final BasicInformationRepo basicInformationRepo;

    public ExperienceInformationServiceImpl(ExperienceInformationRepo experienceInformationRepo, ModelMapper modelMapper, BasicInformationRepo basicInformationRepo) {
        this.experienceInformationRepo = experienceInformationRepo;
        this.basicInformationRepo = basicInformationRepo;
    }

    @Override
    public ExperienceInformationDto createExperienceInformation(ExperienceInformationDto experienceInformationDto, Short basicInfoId) {
        // Convert DTO to entity
        ExperienceInformation experienceInformation = dtoToExperienceInfo(experienceInformationDto, basicInfoId);

        // Save entity
        ExperienceInformation savedExperienceInformation = experienceInformationRepo.save(experienceInformation);

        // Convert entity back to DTO and return
        return experienceInfoToDto(savedExperienceInformation);
    }

    @Override
    public ExperienceInformationDto updateExperienceInformation(ExperienceInformationDto experienceInformationDto, Short experienceInfoId) {
        // Retrieve existing entity from repository
        ExperienceInformation existingExperienceInformation = experienceInformationRepo.findById(experienceInfoId).orElse(null);

        if (existingExperienceInformation != null) {
            // Update entity with DTO data
            existingExperienceInformation.setCompanyName(experienceInformationDto.getCompanyName());
            existingExperienceInformation.setCompanyAddress(experienceInformationDto.getCompanyAddress());
            existingExperienceInformation.setCompanyContact(experienceInformationDto.getCompanyContact());
            existingExperienceInformation.setToDate(experienceInformationDto.getToDate());
            existingExperienceInformation.setToPresent(experienceInformationDto.getToPresent());
            existingExperienceInformation.setBasicInformation(existingExperienceInformation.getBasicInformation());

            // Save updated entity
            ExperienceInformation updatedExperienceInformation = experienceInformationRepo.save(existingExperienceInformation);

            // Convert updated entity to DTO and return
            return experienceInfoToDto(updatedExperienceInformation);
        }
        return null;
    }

    @Override
    public void deleteExperienceInformation(Short experienceInfoId) {

        // Delete entity from repository
        experienceInformationRepo.deleteById(experienceInfoId);
    }

    @Override
    public ExperienceInformationDto getExperienceInfoById(Short experienceInfoId) {
        // Retrieve entity from repository
        ExperienceInformation experienceInformation = experienceInformationRepo.findById(experienceInfoId)
                .orElseThrow(()-> new ResourceNotFoundException("Experience info", "id", experienceInfoId));

        // Convert entity to DTO and return
        return experienceInfoToDto(experienceInformation);
    }

    @Override
    public List<ExperienceInformationDto> getAllExperienceInformation() {
        // Retrieve all entities from repository
        List<ExperienceInformation> experienceInformationList = experienceInformationRepo.findAll();

        // Convert entities to DTOs
        List<ExperienceInformationDto> dtos = new ArrayList<>();
        for (ExperienceInformation experienceInformation : experienceInformationList) {
            dtos.add(experienceInfoToDto(experienceInformation));
        }
        return dtos;
    }

    @Override
    public List<ExperienceInformationDto> getExperienceInfoByBasicInfoId(Short basicInfoId) {
        return toDto(experienceInformationRepo.findExperienceInformationByBasicInformation(new BasicInformation(basicInfoId)));
    }


    // Helper method to convert DTO to entity
    public ExperienceInformation dtoToExperienceInfo(ExperienceInformationDto experienceInformationDto, Short basicInfoId) {

        BasicInformation basicInformation = basicInformationRepo.findById(basicInfoId).orElseThrow(() -> new ResourceNotFoundException("Basic Information", "Id", basicInfoId));

        ExperienceInformation experienceInformation = new ExperienceInformation();
        experienceInformation.setId(experienceInformationDto.getId());
        experienceInformation.setCompanyName(experienceInformationDto.getCompanyName());
        experienceInformation.setCompanyAddress(experienceInformationDto.getCompanyAddress());
        experienceInformation.setCompanyContact(experienceInformationDto.getCompanyContact());
        experienceInformation.setFromDate(experienceInformationDto.getFromDate());
        experienceInformation.setToDate(experienceInformationDto.getToDate());
        experienceInformation.setToPresent(experienceInformationDto.getToPresent());
        experienceInformation.setBasicInformation(basicInformation);

        return experienceInformation;
    }

    // Helper method to convert entity to DTO
    public ExperienceInformationDto experienceInfoToDto(ExperienceInformation experienceInformation) {

        BasicInformationDto basicInformationDto = new BasicInformationDto();

        basicInformationDto.setId(experienceInformation.getBasicInformation().getId());
        basicInformationDto.setBackground(experienceInformation.getBasicInformation().getBackground());
        basicInformationDto.setEmail(experienceInformation.getBasicInformation().getEmail());
        basicInformationDto.setTitle(experienceInformation.getBasicInformation().getTitle());
        basicInformationDto.setFirstName(experienceInformation.getBasicInformation().getFirstName());
        basicInformationDto.setMiddleName(experienceInformation.getBasicInformation().getMiddleName());
        basicInformationDto.setLastName(experienceInformation.getBasicInformation().getLastName());
        basicInformationDto.setMobileNumber(experienceInformation.getBasicInformation().getMobileNumber());
        basicInformationDto.setLinkedinUrl(experienceInformation.getBasicInformation().getLinkedinUrl());
        basicInformationDto.setProfileImage(experienceInformation.getBasicInformation().getProfileImage());


            ExperienceInformationDto experienceInformationDto = new ExperienceInformationDto();
            experienceInformationDto.setId(experienceInformation.getId());
            experienceInformationDto.setCompanyName(experienceInformation.getCompanyName());
            experienceInformationDto.setCompanyAddress(experienceInformation.getCompanyAddress());
            experienceInformationDto.setCompanyContact(experienceInformation.getCompanyContact());
            experienceInformationDto.setFromDate(experienceInformation.getFromDate());
            experienceInformationDto.setToDate(experienceInformation.getToDate());
            experienceInformationDto.setToPresent(experienceInformation.getToPresent());
            experienceInformationDto.setBasicInformation(basicInformationDto);

            return experienceInformationDto;

    }

    public List<ExperienceInformationDto> toDto(List<ExperienceInformation> educationInformationList){
        return educationInformationList.stream().map(this::experienceInfoToDto).collect(Collectors.toList());
    }

}
