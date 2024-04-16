package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.dto.LocalLevelDto;
import com.cv.cvgenarator.dto.ProjectInformationDto;

import java.util.List;

public interface ProjectInformationService {

    // create
    ProjectInformationDto createProjectInformation(ProjectInformationDto projectInformationDto, Short experienceInfoId);

    //update
    ProjectInformationDto updateProjectInformation(ProjectInformationDto projectInformationDto, Short projectInfoId);

    //delete
    void deleteProjectInformation(Short projectId);

    // get
    ProjectInformationDto getProjectInformationById(Short projectId);

    // get all
    List<ProjectInformationDto> getAllProjectInfo();

    //get by experience id

    List<ProjectInformationDto> getProjectInfoByExperienceInfoId(Short experienceInfoId);
    // get by basic id
    List<ProjectInformationDto> getProjectInfoByBasicInfoId(Short basicInfoId);
}
