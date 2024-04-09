package com.cv.cvgenarator.controller;


import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.ExperienceInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience-info")
public class ExperienceInformationController {

    private final ExperienceInformationService experienceInformationService;

    public ExperienceInformationController(ExperienceInformationService experienceInformationService) {
        this.experienceInformationService = experienceInformationService;
    }

    //create
    @PostMapping("/basic-info/{basic-info-id}/experience")
    public ResponseEntity<ResponseDto> createExperienceInfo(@RequestBody ExperienceInformationDto experienceInformationDto,
                                                            @PathVariable ("basic-info-id") Short basicInfoId){

        ExperienceInformationDto createdExperienceInfo  = experienceInformationService.createExperienceInformation(experienceInformationDto, basicInfoId);
        return ResponseEntity.ok(new ResponseDto
                ("Experience Information Created successfully!!: अनुभव  जानकारी सफलतापूर्वक सिर्जना गरियो |", true, createdExperienceInfo ));

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
        return ResponseEntity.ok(new ResponseDto("Experience Information Deleted Successfully!! : अनुभव  जानकारी सफलतापूर्वक मेटियो", true, null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getExperienceInfoById(@PathVariable Short id) {

        ExperienceInformationDto experienceInformationById = experienceInformationService.getExperienceInfoById(id);

        return experienceInformationById != null ?
                ResponseEntity.ok(new ResponseDto("Experience Information extracted by id!! : id द्वारा निकालिएको अनुभव  जानकारी |", true, experienceInformationById)):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllExperienceInfo() {

        List<ExperienceInformationDto> allExperienceInfo = experienceInformationService.getAllExperienceInformation();
        return ResponseEntity.ok
                (new ResponseDto("All Experience Information extracted!! : सबै अनुभव  जानकारी निकालियो |", true, allExperienceInfo));
    }


}
