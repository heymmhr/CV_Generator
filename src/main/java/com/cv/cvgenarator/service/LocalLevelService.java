package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.dto.LocalLevelDto;

import java.util.List;

public interface LocalLevelService {

    // create
    LocalLevelDto createLocalLevel(LocalLevelDto localLevelDto, Short districtId);

    //update
    LocalLevelDto updateLocalLevel(LocalLevelDto localLevelDto, Short localId);

    //delete
    void deleteLocalLevel(Short localId);

    // get
    LocalLevelDto getLocalLevelById(Short localId);

    // get all
    List<LocalLevelDto> getAllLocalLevel();
}
