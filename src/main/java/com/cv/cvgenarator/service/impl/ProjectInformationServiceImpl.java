package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.dto.ProjectInformationDto;
import com.cv.cvgenarator.entity.EducationInformation;
import com.cv.cvgenarator.entity.ExperienceInformation;
import com.cv.cvgenarator.entity.ProjectInformation;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.ExperienceInformationRepo;
import com.cv.cvgenarator.repo.ProjectInformationRepo;
import com.cv.cvgenarator.service.ProjectInformationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectInformationServiceImpl implements ProjectInformationService {

    private final ProjectInformationRepo projectInformationRepo;

    private final ExperienceInformationRepo experienceInformationRepo;

    public ProjectInformationServiceImpl(ProjectInformationRepo projectInformationRepo, ExperienceInformationRepo experienceInformationRepo) {
        this.projectInformationRepo = projectInformationRepo;
        this.experienceInformationRepo = experienceInformationRepo;
    }


    @Override
    public ProjectInformationDto createProjectInformation(ProjectInformationDto projectInformationDto, Short experienceInfoId) {
        ProjectInformation projectInformation = dtoToProjectInfo(projectInformationDto, experienceInfoId);
        ProjectInformation createProjectInfo = projectInformationRepo.save(projectInformation);
        return projectInfoToDto(createProjectInfo, experienceInfoId);
    }

    @Override
    public ProjectInformationDto updateProjectInformation(ProjectInformationDto projectInformationDto, Short projectInfoId) {
        // Retrieve existing entity from repository
        ProjectInformation existingProjectInformation = projectInformationRepo.findById(projectInfoId).orElse(null);

        if (existingProjectInformation != null) {
            // Update entity with DTO data
            existingProjectInformation.setProjectName(projectInformationDto.getProjectName());
            existingProjectInformation.setProjectStatus(projectInformationDto.getProjectStatus());
            existingProjectInformation.setProjectRole(projectInformationDto.getProjectRole());
            existingProjectInformation.setProjectDescription(projectInformationDto.getProjectDescription());
            existingProjectInformation.setTechStack(projectInformationDto.getTechStack());
            existingProjectInformation.setProjectUrl(projectInformationDto.getProjectUrl());
            existingProjectInformation.setExperienceInformation(existingProjectInformation.getExperienceInformation());

            // Save updated entity
            ProjectInformation updatedProjectInformation = projectInformationRepo.save(existingProjectInformation);

            // Convert updated entity to DTO and return
            return projectInfoToDto(updatedProjectInformation, projectInfoId);
        }
        return null;
    }

    @Override
    public void deleteProjectInformation(Short projectInfoId) {

        // Delete entity from repository
        projectInformationRepo.deleteById(projectInfoId);
    }

    @Override
    public ProjectInformationDto getProjectInformationById(Short projectInfoId) {
        // Retrieve entity from repository
        ProjectInformation projectInformation = projectInformationRepo.findById(projectInfoId).orElse(null);

        // Convert entity to DTO and return
        return projectInfoToDto(projectInformation, projectInfoId);
    }

    @Override
    public List<ProjectInformationDto> getAllProjectInfo() {
        // Retrieve all entities from repository
        List<ProjectInformation> projectInformationList = projectInformationRepo.findAll();

        // Convert entities to DTOs
        List<ProjectInformationDto> dtos = new ArrayList<>();
        for (ProjectInformation projectInformation : projectInformationList) {
            dtos.add(projectInfoToDto(projectInformation, null));
        }
        return dtos;
    }

    public ProjectInformation dtoToProjectInfo(ProjectInformationDto projectInformationDto, Short experienceInfoId) {

        ExperienceInformation experienceInformation = experienceInformationRepo.findById(experienceInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience Information", "Id", experienceInfoId));

        ProjectInformation projectInformation = new ProjectInformation();

        projectInformation.setProjectName(projectInformationDto.getProjectName());
        projectInformation.setProjectStatus(projectInformationDto.getProjectStatus());
        projectInformation.setProjectRole(projectInformationDto.getProjectRole());
        projectInformation.setProjectDescription(projectInformationDto.getProjectDescription());
        projectInformation.setProjectUrl(projectInformationDto.getProjectUrl());
        projectInformation.setTechStack(projectInformationDto.getTechStack());
        projectInformation.setExperienceInformation(experienceInformation);

        return projectInformation;
    }

    public ProjectInformationDto projectInfoToDto(ProjectInformation projectInformation, Short experienceInfoId) {

        ExperienceInformationDto experienceInformationDto = new ExperienceInformationDto();

        experienceInformationDto.setId(projectInformation.getExperienceInformation().getId());

        ProjectInformationDto projectInformationDto = new ProjectInformationDto();

        projectInformationDto.setProjectDescription(projectInformation.getProjectDescription());
        projectInformationDto.setProjectName(projectInformation.getProjectName());
        projectInformationDto.setProjectRole(projectInformation.getProjectRole());
        projectInformationDto.setProjectStatus(projectInformation.getProjectStatus());
        projectInformationDto.setTechStack(projectInformation.getTechStack());
        projectInformationDto.setProjectUrl(projectInformation.getProjectUrl());
        projectInformationDto.setExperienceInformation(experienceInformationDto);

        return projectInformationDto;
    }
}
