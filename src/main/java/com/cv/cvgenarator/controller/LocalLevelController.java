package com.cv.cvgenarator.controller;


import com.cv.cvgenarator.dto.DistrictDto;
import com.cv.cvgenarator.dto.LocalLevelDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.LocalLevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/local-level")
public class LocalLevelController {

    private final LocalLevelService localLevelService;

    public LocalLevelController(LocalLevelService localLevelService) {
        this.localLevelService = localLevelService;
    }

    //create
    @PostMapping("/district/{district-id}/localevel")
    public ResponseEntity<ResponseDto> createLocalLevel(@RequestBody LocalLevelDto localLevelDto,
                                                      @PathVariable("district-id") Short districtId){

        LocalLevelDto createdLocalLevel = localLevelService.createLocalLevel(localLevelDto, districtId);
        return ResponseEntity.ok(new ResponseDto
                ("LocalLevel Created successfully!!: स्थानीय तह सफलतापूर्वक सिर्जना गरियो |", true, createdLocalLevel ));

    }

    // update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateLocalLevel(@RequestBody LocalLevelDto localLevelDto,
                                                      @PathVariable Short id) {

        LocalLevelDto updateLocalLevel = localLevelService.updateLocalLevel(localLevelDto, id);
        return updateLocalLevel != null ?
                ResponseEntity.ok(new ResponseDto
                        ("LocalLevel Updated Successfully!! : स्थानीय तह सफलतापूर्वक अद्यावधिक गरियो |", true, updateLocalLevel)):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteLocalLevel(@PathVariable Short id) {

        localLevelService.deleteLocalLevel(id);
        return ResponseEntity.ok(new
                ResponseDto("LocalLevel Deleted Successfully!! : स्थानीय तह सफलतापूर्वक मेटियो", true, null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getLocalLevelById(@PathVariable Short id) {

        LocalLevelDto localLevelById = localLevelService.getLocalLevelById(id);

        return localLevelById != null ?
                ResponseEntity.ok(new ResponseDto
                        ("LocalLevel extracted by id!! : id द्वारा निकालिएको स्थानीय तह |", true, localLevelById)):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllLocalLevel() {

        List<LocalLevelDto> allDistrict = localLevelService.getAllLocalLevel();
        return ResponseEntity.ok
                (new ResponseDto
                        ("LocalLevel extracted!! : सबै स्थानीय तह निकालियो |", true, allDistrict));
    }
}
