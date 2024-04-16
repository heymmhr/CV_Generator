package com.cv.cvgenarator.controller;


import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.CountryDto;
import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.dto.ProvinceDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.ProvinceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
public class ProvinceController extends BaseController{

    private final ProvinceService provinceService;
    private final CustomMessageSource customMessageSource;

    public ProvinceController(ProvinceService provinceService, CustomMessageSource customMessageSource) {
        this.provinceService = provinceService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.PROVINCE;
    }

    //create
    @PostMapping("/country/{country-id}/province")
    public ResponseEntity<ResponseDto> createProvince(@RequestBody ProvinceDto provinceDto,
                                                            @PathVariable("country-id") Short countryId){


        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), provinceService
                .createProvince(provinceDto,countryId)), HttpStatus.OK);

    }

    // update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProvince(@RequestBody ProvinceDto provinceDto,
                                                            @PathVariable Short id) {

        ProvinceDto updateProvince = provinceService.updateProvince(provinceDto, id);
        return updateProvince != null ?
                ResponseEntity.ok(new ResponseDto
                        ("Province Updated Successfully!! : प्रान्त सफलतापूर्वक अद्यावधिक गरियो |", true, updateProvince)):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProvince(@PathVariable Short id) {

        provinceService.deleteProvince(id);
        return ResponseEntity.ok(successResponse(customMessageSource
                .get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getProvinceById(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET, customMessageSource.get(MessageCodeConstant.PROVINCE)), provinceService
                .getProvinceById(id)), HttpStatus.OK);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllProvince() {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), provinceService.getAllProvinces()), HttpStatus.OK);
    }
}
