package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.CountryDto;
import com.cv.cvgenarator.dto.DistrictDto;

import java.util.List;

public interface DistrictService {

    // create
    DistrictDto createDistrict(DistrictDto districtDto);

    //update
    DistrictDto updateDistrict(DistrictDto districtDto, Short districtId);

    //delete
    void deleteDistrict(Short districtId);

    // get
    DistrictDto getDistrictById(Short districtId);

    // get all
    List<DistrictDto> getAllDistricts();
}
