package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.DistrictDto;
import com.cv.cvgenarator.dto.EducationInformationDto;

import java.util.List;

public interface EducationInformationService {

    // create
    EducationInformationDto createEducationInformation(EducationInformationDto educationInformationDto);

    //update
    EducationInformationDto updateEducationInformation(EducationInformationDto educationInformationDto, Short educationInfoId);

    //delete
    void deleteEducationInformation(Short educationInfoId);

    // get
    EducationInformationDto getEducationInfoById(Short educationInfoId);

    // get all
    List<EducationInformationDto> getAllEducationInformation();
}
