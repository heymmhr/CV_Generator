package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.DistrictDto;
import com.cv.cvgenarator.dto.ProvinceDto;
import com.cv.cvgenarator.entity.District;
import com.cv.cvgenarator.entity.Province;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.DistrictRepo;
import com.cv.cvgenarator.repo.ProvinceRepo;
import com.cv.cvgenarator.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepo districtRepo;

    private final ProvinceRepo provinceRepo;

    public DistrictServiceImpl(DistrictRepo districtRepo, ProvinceRepo provinceRepo) {
        this.districtRepo = districtRepo;
        this.provinceRepo = provinceRepo;
    }

    @Override
    public DistrictDto createDistrict(DistrictDto districtDto, Short provinceId) {
        District district = dtoToDistrict(districtDto, provinceId);
        District createDistrict = districtRepo.save(district);
        return districtToDto(createDistrict, provinceId);
    }

    @Override
    public DistrictDto updateDistrict(DistrictDto districtDto, Short districtId) {
        // Retrieve existing entity from repository
        District existingDistrict = districtRepo.findById(districtId).orElse(null);

        if (existingDistrict != null) {
            // Update entity with DTO data
            existingDistrict.setName(districtDto.getName());
            existingDistrict.setNameNepali(districtDto.getNameNepali());
            existingDistrict.setCode(districtDto.getCode());
            existingDistrict.setProvince(existingDistrict.getProvince());

            // Save updated entity
            District updatedDistrict = districtRepo.save(existingDistrict);

            // Convert updated entity to DTO and return
            return districtToDto(updatedDistrict, districtId);
        }
        return null;
    }

    @Override
    public void deleteDistrict(Short districtId) {

        // Delete entity from repository
        districtRepo.deleteById(districtId);
    }

    @Override
    public DistrictDto getDistrictById(Short districtId) {
        // Retrieve entity from repository
        District district = districtRepo.findById(districtId).orElse(null);

        // Convert entity to DTO and return
        return districtToDto(district, districtId);
    }

    @Override
    public List<DistrictDto> getAllDistricts() {
        // Retrieve all entities from repository
        List<District> districtList = districtRepo.findAll();

        // Convert entities to DTOs
        List<DistrictDto> dtos = new ArrayList<>();
        for (District district : districtList) {
            dtos.add(districtToDto(district, null));
        }
        return dtos;
    }

    // Helper method to convert entity to DTO
    public DistrictDto districtToDto(District district, Short provinceId) {

        ProvinceDto provinceDto = new ProvinceDto();
        provinceDto.setId(district.getProvince().getId());
        provinceDto.setName(district.getProvince().getName());
        provinceDto.setNameNepali(district.getProvince().getNameNepali());
        provinceDto.setCode(district.getProvince().getCode());

        /*CountryDto countryDto = new CountryDto();
        countryDto.setId(district.getProvince().getId());
        countryDto.setName(district.getProvince().getName());
        countryDto.setNameNepali(district.getProvince().getNameNepali());*/


        if (district != null) {

            DistrictDto districtDto = new DistrictDto();
            districtDto.setId(district.getId());
            districtDto.setName(district.getName());
            districtDto.setCode(district.getCode());
            districtDto.setNameNepali(district.getNameNepali());
            districtDto.setProvince(provinceDto);


            return districtDto;
        }
        return null;
    }

    // Helper method to convert DTO to entity
    public District dtoToDistrict(DistrictDto districtDto, Short provinceId) {

        Province province = provinceRepo.findById(provinceId)
                .orElseThrow(() -> new ResourceNotFoundException("Province", "Id", provinceId));

        District district = new District();
        district.setId(districtDto.getId());
        district.setName(districtDto.getName());
        district.setNameNepali(districtDto.getNameNepali());
        district.setCode(districtDto.getCode());
        district.setProvince(province);

        return district;
    }
}
