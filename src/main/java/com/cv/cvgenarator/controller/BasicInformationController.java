package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.BasicInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/basic-info")
public class BasicInformationController {

    private final BasicInformationService basicInformationService;

    public BasicInformationController(BasicInformationService basicInformationService) {
        this.basicInformationService = basicInformationService;
    }

    //create
    @PostMapping("/save")
    public ResponseEntity<ResponseDto> createBasicInfo(@RequestBody BasicInformationDto basicInformationDto) {

        BasicInformationDto basicInformation = basicInformationService.createBasicInformation(basicInformationDto);
        return ResponseEntity.ok(new ResponseDto("Basic Information Created successfully!!: आधारभूत जानकारी सफलतापूर्वक सिर्जना गरियो |", true, basicInformation));
    }

    // update

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateBasicInfo(@RequestBody BasicInformationDto basicInformationDto, @PathVariable Short id) {

        BasicInformationDto updateBasicInfo = basicInformationService.updateBasicInformation(basicInformationDto, id);
        return ResponseEntity.ok(new ResponseDto("Basic Information Updated Successfully!! : आधारभूत जानकारी सफलतापूर्वक अद्यावधिक गरियो |", true, updateBasicInfo));
    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteBasicInfo(@PathVariable Short id) {

        basicInformationService.deleteBasicInformation(id);
        return ResponseEntity.ok(new ResponseDto("Basic Information Deleted Successfully!! : आधारभूत जानकारी सफलतापूर्वक मेटियो", true, null));
    }

    // get

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getBasicInfoById(@PathVariable Short id) {

        BasicInformationDto basicInformationById = basicInformationService.getBasicInformationById(id);
        return ResponseEntity.ok(new ResponseDto("Basic Information extracted by id!! : id द्वारा निकालिएको आधारभूत जानकारी |", true, basicInformationById));
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllBasicInfo() {

        List<BasicInformationDto> allBasicInfo = basicInformationService.getAllBasicInfo();
        return ResponseEntity.ok(new ResponseDto("All Basic Information extracted!! : सबै आधारभूत जानकारी निकालियो |", true, allBasicInfo));
    }

}
