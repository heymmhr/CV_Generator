package com.cv.cvgenarator.controller;


import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.ExperienceInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience-info")
public class ExperienceInformationController extends BaseController{

    private final ExperienceInformationService experienceInformationService;

    private final CustomMessageSource customMessageSource;

    public ExperienceInformationController(ExperienceInformationService experienceInformationService, CustomMessageSource customMessageSource) {
        this.experienceInformationService = experienceInformationService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.EXPERIENCE_INFORMATION;
    }

    //create
    @PostMapping("/basic-info/{basic-info-id}/experience")
    public ResponseEntity<ResponseDto> createExperienceInfo(@RequestBody ExperienceInformationDto experienceInformationDto,
                                                            @PathVariable ("basic-info-id") Short basicInfoId){

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), experienceInformationService
                .createExperienceInformation(experienceInformationDto,basicInfoId)), HttpStatus.OK);
    }

    // update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateExperienceInfo(@RequestBody ExperienceInformationDto experienceInformationDto,
                                                            @PathVariable Short id) {

        ExperienceInformationDto updateExperienceInfo = experienceInformationService.updateExperienceInformation(experienceInformationDto, id);
        return updateExperienceInfo != null ?
                ResponseEntity.ok(new ResponseDto("Experience Information Updated Successfully!! : अनुभव  जानकारी सफलतापूर्वक अद्यावधिक गरियो |", true, updateExperienceInfo)):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteExperienceInfo(@PathVariable Short id) {

        experienceInformationService.deleteExperienceInformation(id);
        return ResponseEntity.ok(successResponse(customMessageSource
                .get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getExperienceInfoById(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET, customMessageSource.get(MessageCodeConstant.EXPERIENCE_INFORMATION)), experienceInformationService
                .getExperienceInfoById(id)), HttpStatus.OK);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllExperienceInfo() {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), experienceInformationService.getAllExperienceInformation()), HttpStatus.OK);
    }


}
