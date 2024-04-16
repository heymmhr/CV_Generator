package com.cv.cvgenarator.controller;


import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
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
public class LocalLevelController extends BaseController{

    private final LocalLevelService localLevelService;

    private final CustomMessageSource customMessageSource;

    public LocalLevelController(LocalLevelService localLevelService, CustomMessageSource customMessageSource) {
        this.localLevelService = localLevelService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.LOCAL_LEVEL;
    }

    //create
    @PostMapping("/district/{district-id}/localevel")
    public ResponseEntity<ResponseDto> createLocalLevel(@RequestBody LocalLevelDto localLevelDto,
                                                      @PathVariable("district-id") Short districtId){

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), localLevelService
                .createLocalLevel(localLevelDto,districtId)), HttpStatus.OK);

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
        return ResponseEntity.ok(successResponse(customMessageSource
                .get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getLocalLevelById(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET, customMessageSource.get(MessageCodeConstant.LOCAL_LEVEL)), localLevelService
                .getLocalLevelById(id)), HttpStatus.OK);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllLocalLevel() {

       /* List<LocalLevelDto> allDistrict = localLevelService.getAllLocalLevel();
        return ResponseEntity.ok
                (new ResponseDto
                        ("LocalLevel extracted!! : सबै स्थानीय तह निकालियो |", true, allDistrict));*/

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), localLevelService.getAllLocalLevel()), HttpStatus.OK);
    }
}
