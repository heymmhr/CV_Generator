package com.cv.cvgenarator.controller;

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
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    //create
    @PostMapping("/province/{province-id}/district")
    public ResponseEntity<ResponseDto> createDistrict(@RequestBody DistrictDto districtDto,
                                                      @PathVariable("province-id") Short provinceId){

        DistrictDto createdDistrict  = districtService.createDistrict(districtDto, provinceId);
        return ResponseEntity.ok(new ResponseDto
                ("District Created successfully!!: जिल्ला सफलतापूर्वक सिर्जना गरियो |", true, createdDistrict ));

    }
    // update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateDistrict(@RequestBody DistrictDto districtDto,
                                                      @PathVariable Short id) {

        DistrictDto updateDistrict = districtService.updateDistrict(districtDto, id);
        return updateDistrict != null ?
                ResponseEntity.ok(new ResponseDto
                        ("District Updated Successfully!! : जिल्ला सफलतापूर्वक अद्यावधिक गरियो |", true, updateDistrict)):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteDistrict(@PathVariable Short id) {

        districtService.deleteDistrict(id);
        return ResponseEntity.ok(new
                ResponseDto("District Deleted Successfully!! : जिल्ला सफलतापूर्वक मेटियो", true, null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getDistrictById(@PathVariable Short id) {

        DistrictDto districtById = districtService.getDistrictById(id);

        return districtById != null ?
                ResponseEntity.ok(new ResponseDto
                        ("District extracted by id!! : id द्वारा निकालिएको जिल्ला |", true, districtById)):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllDistrict() {

        List<DistrictDto> allDistrict = districtService.getAllDistricts();
        return ResponseEntity.ok
                (new ResponseDto
                        ("All District extracted!! : सबै जिल्ला निकालियो |", true, allDistrict));
    }
}
