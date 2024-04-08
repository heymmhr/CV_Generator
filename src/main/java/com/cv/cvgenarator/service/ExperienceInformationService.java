package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.ExperienceInformationDto;

import java.util.List;

public interface ExperienceInformationService {

    // create
    ExperienceInformationDto createExperienceInformation(ExperienceInformationDto experienceInformationDto);

    //update
    ExperienceInformationDto updateExperienceInformation(ExperienceInformationDto experienceInformationDto, Short experienceInfoId);

    //delete
    void deleteExperienceInformation(Short experienceInfoId);

    // get
    ExperienceInformationDto getExperienceInfoById(Short experienceInfoId);

    // get all
    List<ExperienceInformationDto> getAllExperienceInformation();
}
