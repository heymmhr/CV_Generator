package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.DistrictDto;
import com.cv.cvgenarator.dto.LocalLevelDto;
import com.cv.cvgenarator.entity.District;
import com.cv.cvgenarator.entity.LocalLevel;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.DistrictRepo;
import com.cv.cvgenarator.repo.LocalLevelRepo;
import com.cv.cvgenarator.service.LocalLevelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalLevelServiceImpl implements LocalLevelService {

    private final LocalLevelRepo localLevelRepo;

    private final DistrictRepo districtRepo;

    public LocalLevelServiceImpl(LocalLevelRepo localLevelRepo, DistrictRepo districtRepo) {
        this.localLevelRepo = localLevelRepo;
        this.districtRepo = districtRepo;
    }

    @Override
    public LocalLevelDto createLocalLevel(LocalLevelDto localLevelDto, Short districtId) {

        LocalLevel localLevel = dtoToLocalevel(localLevelDto, districtId);
        LocalLevel createLocalLevel = localLevelRepo.save(localLevel);
        return localevelToDto(createLocalLevel, districtId);
    }

    @Override
    public LocalLevelDto updateLocalLevel(LocalLevelDto localLevelDto, Short localId) {
        // Retrieve existing entity from repository
        LocalLevel existingLocalLevel = localLevelRepo.findById(localId).orElse(null);

        if (existingLocalLevel != null) {
            // Update entity with DTO data
            existingLocalLevel.setName(localLevelDto.getName());
            existingLocalLevel.setNameNepali(localLevelDto.getNameNepali());
            existingLocalLevel.setCode(localLevelDto.getCode());
            existingLocalLevel.setTotalWardCount(localLevelDto.getTotalWardCount());
            existingLocalLevel.setDistrict(existingLocalLevel.getDistrict());

            // Save updated entity
            LocalLevel updatedLocallevel = localLevelRepo.save(existingLocalLevel);

            // Convert updated entity to DTO and return
            return localevelToDto(updatedLocallevel, localId);
        }
        return null;
    }

    @Override
    public void deleteLocalLevel(Short localId) {

        // Delete entity from repository
        localLevelRepo.deleteById(localId);
    }

    @Override
    public LocalLevelDto getLocalLevelById(Short localId) {
        // Retrieve entity from repository
        LocalLevel localLevel = localLevelRepo.findById(localId).orElse(null);

        // Convert entity to DTO and return
        return localevelToDto(localLevel, localId);
    }

    @Override
    public List<LocalLevelDto> getAllLocalLevel() {
        // Retrieve all entities from repository
        List<LocalLevel> localLevelList = localLevelRepo.findAll();

        // Convert entities to DTOs
        List<LocalLevelDto> dtos = new ArrayList<>();
        for (LocalLevel localLevel : localLevelList) {
            dtos.add(localevelToDto(localLevel, null));
        }
        return dtos;
    }

    // Helper method to convert entity to DTO

    public LocalLevelDto localevelToDto(LocalLevel localLevel, Short districtId){

        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(localLevel.getDistrict().getId());
        districtDto.setName(localLevel.getDistrict().getName());
        districtDto.setNameNepali(localLevel.getDistrict().getNameNepali());
        districtDto.setCode(localLevel.getDistrict().getCode());

        if (localLevel != null){

            LocalLevelDto localLevelDto = new LocalLevelDto();
            localLevelDto.setId(localLevel.getId());
            localLevelDto.setName(localLevel.getName());
            localLevelDto.setCode(localLevel.getCode());
            localLevelDto.setNameNepali(localLevel.getNameNepali());
            localLevelDto.setTotalWardCount(localLevel.getTotalWardCount());
            localLevelDto.setDistrict(districtDto);

            return localLevelDto;
        }
        return null;
    }

    // Helper method to convert DTO to entity

    public LocalLevel dtoToLocalevel (LocalLevelDto localLevelDto, Short districtId){

        District district = districtRepo.findById(districtId)
                .orElseThrow(()-> new ResourceNotFoundException("District", "Id", districtId));

        LocalLevel localLevel = new LocalLevel();
        localLevel.setId(localLevelDto.getId());
        localLevel.setName(localLevelDto.getName());
        localLevel.setCode(localLevelDto.getCode());
        localLevel.setNameNepali(localLevelDto.getNameNepali());
        localLevel.setTotalWardCount(localLevelDto.getTotalWardCount());
        localLevel.setDistrict(district);

        return localLevel;
    }
}
