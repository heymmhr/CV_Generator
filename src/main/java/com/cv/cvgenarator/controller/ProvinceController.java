package com.cv.cvgenarator.controller;


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
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    //create
    @PostMapping("/country/{country-id}/province")
    public ResponseEntity<ResponseDto> createProvince(@RequestBody ProvinceDto provinceDto,
                                                            @PathVariable("country-id") Short countryId){

        ProvinceDto createdProvince  = provinceService.createProvince(provinceDto, countryId);
        return ResponseEntity.ok(new ResponseDto
                ("Province Created successfully!!: प्रान्त सफलतापूर्वक सिर्जना गरियो |", true, createdProvince ));

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
        return ResponseEntity.ok(new
                ResponseDto("Province Deleted Successfully!! : प्रान्त सफलतापूर्वक मेटियो", true, null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getProvinceById(@PathVariable Short id) {

        ProvinceDto provinceById = provinceService.getProvinceById(id);

        return provinceById != null ?
                ResponseEntity.ok(new ResponseDto
                        ("Province extracted by id!! : id द्वारा निकालिएको प्रान्त |", true, provinceById)):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllProvince() {

        List<ProvinceDto> allProvince = provinceService.getAllProvinces();
        return ResponseEntity.ok
                (new ResponseDto
                        ("All Province extracted!! : सबै प्रान्त निकालियो |", true, allProvince));
    }
}
