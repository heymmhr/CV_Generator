package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.ProjectInformationDto;
import com.cv.cvgenarator.dto.ProvinceDto;

import java.util.List;

public interface ProvinceService {

    // create
    ProvinceDto createProvince(ProvinceDto provinceDto);

    //update
    ProvinceDto updateProvince(ProvinceDto provinceDto, Short provinceId);

    //delete
    void deleteProvince(Short provinceId);

    // get
    ProvinceDto getProvinceById(Short provinceId);

    // get all
    List<ProvinceDto> getAllProvinces();
}
