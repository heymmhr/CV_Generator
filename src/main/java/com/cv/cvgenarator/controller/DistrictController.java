package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.DistrictDto;
import com.cv.cvgenarator.dto.ProvinceDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/district")
public class DistrictController extends BaseController{

    private final DistrictService districtService;

    private final CustomMessageSource customMessageSource;
    public DistrictController(DistrictService districtService, CustomMessageSource customMessageSource) {
        this.districtService = districtService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.DISTRICT;
    }

    //create
    @PostMapping("/province/{province-id}/district")
    public ResponseEntity<ResponseDto> createDistrict(@RequestBody DistrictDto districtDto,
                                                      @PathVariable("province-id") Short provinceId){

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), districtService
                .createDistrict(districtDto, provinceId)), HttpStatus.OK);

    }
    // update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateDistrict(@RequestBody DistrictDto districtDto,
                                                      @PathVariable Short id) {

        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_UPDATE, customMessageSource
                        .get(MessageCodeConstant.DISTRICT)), districtService
                .updateDistrict(districtDto, id)), HttpStatus.OK);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteDistrict(@PathVariable Short id) {

        districtService.deleteDistrict(id);
        return ResponseEntity.ok(
                successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getDistrictById(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET, customMessageSource
                        .get(MessageCodeConstant.DISTRICT)), districtService.getDistrictById(id)), HttpStatus.OK);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllDistrict() {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), districtService.getAllDistricts()), HttpStatus.OK);
    }
}
