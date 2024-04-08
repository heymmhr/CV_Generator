package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.AddressInformationDto;
import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.entity.BasicInformation;

import java.util.List;

public interface BasicInformationService {

    // create
    BasicInformationDto createBasicInformation(BasicInformationDto basicInformationDto);

    //update
    BasicInformationDto updateBasicInformation(BasicInformationDto basicInformationDto, Short basicInfoId);

    //delete
    void deleteBasicInformation(Short basicInfoId);

    // get
    BasicInformationDto getBasicInformationById(Short basicInfoId);

    // get all
    List<BasicInformationDto> getAllBasicInfo();
}
