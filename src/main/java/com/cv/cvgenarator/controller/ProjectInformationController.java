package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.ProjectInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.ProjectInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project-info")
public class ProjectInformationController extends BaseController{

    private final ProjectInformationService projectInformationService;
    private final CustomMessageSource customMessageSource;

    public ProjectInformationController(ProjectInformationService projectInformationService, CustomMessageSource customMessageSource) {
        this.projectInformationService = projectInformationService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.PROJECT_INFORMATION;
    }

    //create
    @PostMapping("/experience-info/{experience-info-id}/project")
    public ResponseEntity<ResponseDto> createProjectInfo(@RequestBody ProjectInformationDto projectInformationDto,
                                                         @PathVariable("experience-info-id") Short experienceInfoId) {


        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), projectInformationService
                .createProjectInformation(projectInformationDto,experienceInfoId)), HttpStatus.OK);
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
        return ResponseEntity.ok(successResponse(customMessageSource
                .get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)),null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getProjectInfoById(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET, customMessageSource.get(MessageCodeConstant.PROJECT_INFORMATION)), projectInformationService
                .getProjectInformationById(id)), HttpStatus.OK);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllProjectInfo() {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), projectInformationService.getAllProjectInfo()), HttpStatus.OK);
    }

    // get by experienceId

    @GetMapping("by-experience-id/{experience-id}")
    public ResponseEntity<ResponseDto> getProjectInfoByExperienceId(@PathVariable ("experience-id") Short experienceId){
        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET, customMessageSource
                        .get(messageCode)), projectInformationService.getProjectInfoByExperienceInfoId(experienceId)), HttpStatus.OK);
    }

    //get by basicId

    @GetMapping("by-basic-id/{basic-info-id}")
    public ResponseEntity<ResponseDto>getProjectInfoByBasicInfoId(@PathVariable("basic-info-id") Short basicInfoId){
        return new ResponseEntity<> (successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET, customMessageSource
                .get(messageCode)), projectInformationService.getProjectInfoByBasicInfoId(basicInfoId)),HttpStatus.OK);
    }

}
