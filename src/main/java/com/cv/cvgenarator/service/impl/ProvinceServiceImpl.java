package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.CountryDto;
import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.dto.ProvinceDto;
import com.cv.cvgenarator.entity.*;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.CountryRepo;
import com.cv.cvgenarator.repo.ProvinceRepo;
import com.cv.cvgenarator.service.ProvinceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepo provinceRepo;
    private final CountryRepo countryRepo;

    public ProvinceServiceImpl(ProvinceRepo provinceRepo, ModelMapper modelMapper, CountryRepo countryRepo) {
        this.provinceRepo = provinceRepo;
        this.countryRepo = countryRepo;
    }

    @Override
    public ProvinceDto createProvince(ProvinceDto provinceDto, Short countryId) {
        Province province = dtoToProvince(provinceDto, countryId);
        Province createProvince = provinceRepo.save(province);
        return provinceToDto(createProvince, countryId);
    }

    @Override
    public ProvinceDto updateProvince(ProvinceDto provinceDto, Short provinceId) {
        // Retrieve existing entity from repository
        Province existingProvince = provinceRepo.findById(provinceId).orElse(null);

        if (existingProvince != null) {
            // Update entity with DTO data
            existingProvince.setName(provinceDto.getName());
            existingProvince.setNameNepali(provinceDto.getNameNepali());
            existingProvince.setCode(provinceDto.getCode());
            existingProvince.setCountry(existingProvince.getCountry());

            // Save updated entity
            Province updatedProvince = provinceRepo.save(existingProvince);

            // Convert updated entity to DTO and return
            return provinceToDto(updatedProvince, provinceId);
        }
        return null;
    }

    @Override
    public void deleteProvince(Short provinceId) {

        // Delete entity from repository
        provinceRepo.deleteById(provinceId);
    }

    @Override
    public ProvinceDto getProvinceById(Short provinceId) {
        // Retrieve entity from repository
        Province province = provinceRepo.findById(provinceId).orElse(null);

        // Convert entity to DTO and return
        return provinceToDto(province, provinceId);
    }

    @Override
    public List<ProvinceDto> getAllProvinces() {
        // Retrieve all entities from repository
        List<Province> provinceList = provinceRepo.findAll();

        // Convert entities to DTOs
        List<ProvinceDto> dtos = new ArrayList<>();
        for (Province province : provinceList) {
            dtos.add(provinceToDto(province, null));
        }
        return dtos;
    }

    // Helper method to convert entity to DTO
    public ProvinceDto provinceToDto(Province province, Short countryId) {

        CountryDto countryDto = new CountryDto();
        countryDto.setId(province.getCountry().getId());
        countryDto.setName(province.getCountry().getName());
        countryDto.setNameNepali(province.getCountry().getNameNepali());

        if (province != null) {
            ProvinceDto provinceDto = new ProvinceDto();
            provinceDto.setId(province.getId());
            provinceDto.setName(province.getName());
            provinceDto.setNameNepali(province.getNameNepali());
            provinceDto.setCode(province.getCode());
            provinceDto.setCountry(countryDto);

            return provinceDto;
        }
        return null;
    }

    // Helper method to convert DTO to entity
    public Province dtoToProvince(ProvinceDto provinceDto, Short countryId) {

        Country country = countryRepo.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "Id", countryId));

        Province province = new Province();
        province.setId(provinceDto.getId());
        province.setName(provinceDto.getName());
        province.setNameNepali(provinceDto.getNameNepali());
        province.setCode(provinceDto.getCode());
        province.setCountry(country);

        return province;
    }

}
