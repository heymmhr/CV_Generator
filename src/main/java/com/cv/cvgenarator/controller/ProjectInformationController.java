package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.dto.ProjectInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.ProjectInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project-info")
public class ProjectInformationController {

    private final ProjectInformationService projectInformationService;

    public ProjectInformationController(ProjectInformationService projectInformationService) {
        this.projectInformationService = projectInformationService;
    }

    //create
    @PostMapping("/experience-info/{experience-info-id}/project")
    public ResponseEntity<ResponseDto> createProjectInfo(@RequestBody ProjectInformationDto projectInformationDto,
                                                         @PathVariable("experience-info-id") Short experienceInfoId) {

        ProjectInformationDto createdProjectInfo = projectInformationService
                .createProjectInformation(projectInformationDto, experienceInfoId);
        return ResponseEntity.ok(new ResponseDto
                ("Project Information Created successfully!!: परियोजना जानकारी सफलतापूर्वक सिर्जना गरियो |", true, createdProjectInfo));

    }

    // update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProjectInfo(@RequestBody ProjectInformationDto projectInformationDto,
                                                         @PathVariable Short id) {

        ProjectInformationDto updateProjectInfo = projectInformationService.updateProjectInformation(projectInformationDto, id);
        return updateProjectInfo != null ?
                ResponseEntity.ok(new
                        ResponseDto("Project Information Updated Successfully!! : परियोजना  जानकारी सफलतापूर्वक अद्यावधिक गरियो |", true, updateProjectInfo)) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProjectInfo(@PathVariable Short id) {

        projectInformationService.deleteProjectInformation(id);
        return ResponseEntity.ok(new ResponseDto("Project Information Deleted Successfully!! : परियोजना  जानकारी सफलतापूर्वक मेटियो", true, null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getProjectInfoById(@PathVariable Short id) {

        ProjectInformationDto projectInformationById = projectInformationService.getProjectInformationById(id);

        return projectInformationById != null ?
                ResponseEntity
                        .ok(new ResponseDto("Project Information extracted by id!! : id द्वारा निकालिएको परियोजना जानकारी |", true, projectInformationById)) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllProjectInfo() {

        List<ProjectInformationDto> allExperienceInfo = projectInformationService.getAllProjectInfo();
        return ResponseEntity.ok
                (new ResponseDto("All Project Information extracted!! : सबै परियोजना जानकारी निकालियो |", true, allExperienceInfo));
    }

}
