package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.EducationInformationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education-info")
public class EducationInformationController extends BaseController{

    private final EducationInformationService educationInformationService;
    private final CustomMessageSource customMessageSource;

    public EducationInformationController(EducationInformationService educationInformationService, CustomMessageSource customMessageSource) {
        this.educationInformationService = educationInformationService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.EDUCATION_INFORMATION;
    }


    //create
    @PostMapping("/basic-info/{basic-info-id}/education")
    public ResponseEntity<ResponseDto> createEducationInfo(@Valid @RequestBody EducationInformationDto educationInformationDto,
                                                           @PathVariable("basic-info-id") Short basicInfoId) {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), educationInformationService
                .createEducationInformation(educationInformationDto,basicInfoId)), HttpStatus.OK);

    }

    // update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateEducationInfo(@Valid @RequestBody EducationInformationDto educationInformationDto,
                                                           @PathVariable Short id) {

        EducationInformationDto updateEducationInfo = educationInformationService.updateEducationInformation(educationInformationDto, id);
        return updateEducationInfo != null ?
                ResponseEntity.ok(new ResponseDto("Education Information Updated Successfully!! : शिक्षा  जानकारी सफलतापूर्वक अद्यावधिक गरियो |", true, updateEducationInfo)) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteEducationInfo(@PathVariable Short id) {

        educationInformationService.deleteEducationInformation(id);
        return ResponseEntity.ok(
                successResponse(customMessageSource
                        .get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));

    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEducationInfoById(@PathVariable Short id) {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET, customMessageSource
                        .get(MessageCodeConstant.EDUCATION_INFORMATION)), educationInformationService
                .getEducationInfoById(id)), HttpStatus.OK);

    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllEducationInfo() {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), educationInformationService.getAllEducationInformation()), HttpStatus.OK);

    }

    @GetMapping("/by-basic-id/{basic-info-id}")
    public ResponseEntity<ResponseDto> getEducationByBasicId(@PathVariable("basic-info-id") Short basicInfoId){
        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET, customMessageSource
                        .get(messageCode)),educationInformationService.getEducationInfoByBasicInfoId(basicInfoId)),HttpStatus.OK);
    }
}
