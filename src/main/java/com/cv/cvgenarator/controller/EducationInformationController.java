package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.EducationInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education-info")
public class EducationInformationController {

    private final EducationInformationService educationInformationService;

    public EducationInformationController(EducationInformationService educationInformationService) {
        this.educationInformationService = educationInformationService;
    }


    //create
    @PostMapping("/basic-info/{basic-info-id}/education")
    public ResponseEntity<ResponseDto> createEducationInfo(@RequestBody EducationInformationDto educationInformationDto,
                                                           @PathVariable("basic-info-id") Short basicInfoId) {

        EducationInformationDto createdEducationInfo = educationInformationService
                .createEducationInformation(educationInformationDto, basicInfoId);
        return ResponseEntity.ok(new ResponseDto
                ("Education Information Created successfully!!: शिक्षा जानकारी सफलतापूर्वक सिर्जना गरियो |", true, createdEducationInfo));

    }

    // update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateEducationInfo(@RequestBody EducationInformationDto educationInformationDto,
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
        return ResponseEntity.ok(new ResponseDto("Education Information Deleted Successfully!! : शिक्षा  जानकारी सफलतापूर्वक मेटियो", true, null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEducationInfoById(@PathVariable Short id) {

        EducationInformationDto educationInformationById = educationInformationService.getEducationInfoById(id);

        return educationInformationById != null ?
                ResponseEntity
                        .ok(new ResponseDto("Education Information extracted by id!! : id द्वारा निकालिएको शिक्षा जानकारी |", true, educationInformationById)) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllEducationInfo() {

        List<EducationInformationDto> allExperienceInfo = educationInformationService.getAllEducationInformation();
        return ResponseEntity.ok
                (new ResponseDto("All Education Information extracted!! : सबै शिक्षा  जानकारी निकालियो |", true, allExperienceInfo));
    }
}
