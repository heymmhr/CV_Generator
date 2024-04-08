package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.LocalLevelDto;
import com.cv.cvgenarator.dto.ProjectInformationDto;

import java.util.List;

public interface ProjectInformationService {

    // create
    ProjectInformationDto createProjectInformation(ProjectInformationDto projectInformationDto);

    //update
    ProjectInformationDto updateProjectInformation(ProjectInformationDto projectInformationDto, Short projectId);

    //delete
    void deleteProjectInformation(Short projectId);

    // get
    ProjectInformationDto getProjectInformationById(Short projectId);

    // get all
    List<ProjectInformationDto> getAllProjectInfo();
}
