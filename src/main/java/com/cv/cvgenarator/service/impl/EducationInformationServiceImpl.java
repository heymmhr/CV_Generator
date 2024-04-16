package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.EducationInformation;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.BasicInformationRepo;
import com.cv.cvgenarator.repo.EducationInformationRepo;
import com.cv.cvgenarator.service.EducationInformationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationInformationServiceImpl implements EducationInformationService {

    private final EducationInformationRepo educationInformationRepo;

    private final BasicInformationRepo basicInformationRepo;

    public EducationInformationServiceImpl(EducationInformationRepo educationInformationRepo, BasicInformationRepo basicInformationRepo) {
        this.educationInformationRepo = educationInformationRepo;
        this.basicInformationRepo = basicInformationRepo;
    }


    @Override
    public EducationInformationDto createEducationInformation(EducationInformationDto educationInformationDto, Short basicInfoId) {

        EducationInformation educationInformation = dtoToEducationInfo(educationInformationDto, basicInfoId);
        EducationInformation createEducationInfo = educationInformationRepo.save(educationInformation);
        return educationInfoToDto(createEducationInfo);
    }

    @Override
    public EducationInformationDto updateEducationInformation(EducationInformationDto educationInformationDto, Short educationInfoId) {
        // Retrieve existing entity from repository
        EducationInformation existingEducationInformation = educationInformationRepo.findById(educationInfoId).orElse(null);

        if (existingEducationInformation != null) {
            // Update entity with DTO data
            existingEducationInformation.setEducationDescription(educationInformationDto.getEducationDescription());
            existingEducationInformation.setDegreeName(educationInformationDto.getDegreeName());
            existingEducationInformation.setInstitutionAddress(educationInformationDto.getInstitutionAddress());
            existingEducationInformation.setInstitutionContact(educationInformationDto.getInstitutionContact());
            existingEducationInformation.setInstitutionName(educationInformationDto.getInstitutionName());
            existingEducationInformation.setFromDate(educationInformationDto.getFromDate());
            existingEducationInformation.setToDate(educationInformationDto.getToDate());
            existingEducationInformation.setToPresent(educationInformationDto.getToPresent());
            existingEducationInformation.setBasicInformation(existingEducationInformation.getBasicInformation());

            // Save updated entity
            EducationInformation updatedEducationInformation = educationInformationRepo.save(existingEducationInformation);

            // Convert updated entity to DTO and return
            return educationInfoToDto(updatedEducationInformation);
        }
        return null;
    }

    @Override
    public void deleteEducationInformation(Short educationInfoId) {

        // Delete entity from repository
        educationInformationRepo.deleteById(educationInfoId);
    }

    @Override
    public EducationInformationDto getEducationInfoById(Short educationInfoId) {
        // Retrieve entity from repository
        EducationInformation educationInformation = educationInformationRepo.findById(educationInfoId).orElse(null);

        // Convert entity to DTO and return
        return educationInfoToDto(educationInformation);

    }
    @Override
    public List<EducationInformationDto> getEducationInfoByBasicInfoId(Short basicInfoId) {
        return  toDto(educationInformationRepo.findEducationInformationByBasicInformation(new BasicInformation(basicInfoId)));
    }

    @Override
    public List<EducationInformationDto> getAllEducationInformation() {
        // Retrieve all entities from repository
        List<EducationInformation> educationInformationList = educationInformationRepo.findAll();

        // Convert entities to DTOs
        List<EducationInformationDto> dtos = new ArrayList<>();
        for (EducationInformation educationInformation : educationInformationList) {
            dtos.add(educationInfoToDto(educationInformation));
        }
        return dtos;
    }

    public EducationInformation dtoToEducationInfo(EducationInformationDto educationInformationDto, Short basicInfoId) {

        BasicInformation basicInformation = basicInformationRepo.findById(basicInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information", "Id", basicInfoId));

        EducationInformation educationInformation = new EducationInformation();

        educationInformation.setInstitutionName(educationInformationDto.getInstitutionName());
        educationInformation.setInstitutionAddress(educationInformationDto.getInstitutionAddress());
        educationInformation.setInstitutionContact(educationInformationDto.getInstitutionContact());
        educationInformation.setFromDate(educationInformationDto.getFromDate());
        educationInformation.setToDate(educationInformationDto.getToDate());
        educationInformation.setToPresent(educationInformationDto.getToPresent());
        educationInformation.setDegreeName(educationInformationDto.getDegreeName());
        educationInformation.setEducationDescription(educationInformationDto.getEducationDescription());
        educationInformation.setBasicInformation(basicInformation);

        return educationInformation;
    }

    public EducationInformationDto educationInfoToDto(EducationInformation educationInformation) {

        BasicInformationDto basicInformationDto = new BasicInformationDto();

        basicInformationDto.setId(educationInformation.getBasicInformation().getId());
        basicInformationDto.setBackground(educationInformation.getBasicInformation().getBackground());
        basicInformationDto.setEmail(educationInformation.getBasicInformation().getEmail());
        basicInformationDto.setTitle(educationInformation.getBasicInformation().getTitle());
        basicInformationDto.setFirstName(educationInformation.getBasicInformation().getFirstName());
        basicInformationDto.setMiddleName(educationInformation.getBasicInformation().getMiddleName());
        basicInformationDto.setLastName(educationInformation.getBasicInformation().getLastName());
        basicInformationDto.setMobileNumber(educationInformation.getBasicInformation().getMobileNumber());
        basicInformationDto.setLinkedinUrl(educationInformation.getBasicInformation().getLinkedinUrl());
        basicInformationDto.setProfileImage(educationInformation.getBasicInformation().getProfileImage());


        EducationInformationDto educationInformationDto = new EducationInformationDto();

        educationInformationDto.setId(educationInformation.getBasicInformation().getId());
        educationInformationDto.setInstitutionName(educationInformation.getInstitutionName());
        educationInformationDto.setInstitutionAddress(educationInformation.getInstitutionAddress());
        educationInformationDto.setInstitutionContact(educationInformation.getInstitutionContact());
        educationInformationDto.setFromDate(educationInformation.getFromDate());
        educationInformationDto.setToDate(educationInformation.getToDate());
        educationInformationDto.setToPresent(educationInformation.getToPresent());
        educationInformationDto.setDegreeName(educationInformation.getDegreeName());
        educationInformationDto.setEducationDescription(educationInformation.getEducationDescription());
        educationInformationDto.setBasicInformation(basicInformationDto);

        return educationInformationDto;
    }

    public List<EducationInformationDto> toDto(List<EducationInformation> educationInformationList){
        return educationInformationList.stream().map(this::educationInfoToDto).collect(Collectors.toList());
    }


}
