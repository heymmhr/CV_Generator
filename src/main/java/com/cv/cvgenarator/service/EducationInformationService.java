package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.entity.EducationInformation;

import java.util.List;


public interface EducationInformationService {

    // create
    EducationInformationDto createEducationInformation(EducationInformationDto educationInformationDto, Short basicInfoId);

    //update
    EducationInformationDto updateEducationInformation(EducationInformationDto educationInformationDto, Short educationInfoId);

    //delete
    void deleteEducationInformation(Short educationInfoId);

    // get
    EducationInformationDto getEducationInfoById(Short educationInfoId);


    // get all
    List<EducationInformationDto> getAllEducationInformation();


    // get by basic id
    List<EducationInformationDto> getEducationInfoByBasicInfoId(Short basicInfoId);
}
