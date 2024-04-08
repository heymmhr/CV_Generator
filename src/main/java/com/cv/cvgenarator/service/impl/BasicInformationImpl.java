package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.BasicInformationRepo;
import com.cv.cvgenarator.service.BasicInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicInformationImpl implements BasicInformationService {

    private final BasicInformationRepo basicInformationRepo;

    private final ModelMapper modelMapper;


    public BasicInformationImpl(BasicInformationRepo basicInformationRepo, ModelMapper modelMapper) {
        this.basicInformationRepo = basicInformationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public BasicInformationDto createBasicInformation(BasicInformationDto basicInformationDto) {

        BasicInformation basicInformation = modelMapper.map(basicInformationDto,BasicInformation.class);
        BasicInformation createdBasicInfo = basicInformationRepo.save(basicInformation);
        return modelMapper.map(createdBasicInfo,BasicInformationDto.class);

    }

    @Override
    public BasicInformationDto updateBasicInformation(BasicInformationDto basicInformationDto, Short basicInfoId) {

        BasicInformation basicInformation = basicInformationRepo.findById(basicInfoId)
                .orElseThrow(()-> new ResourceNotFoundException("Basic Information", "Id", basicInfoId));

        basicInformation.setFirstName(basicInformationDto.getFirstName());
        basicInformation.setMiddleName(basicInformationDto.getMiddleName());
        basicInformation.setLastName(basicInformationDto.getLastName());
        basicInformation.setBackground(basicInformationDto.getBackground());
        basicInformation.setTitle(basicInformationDto.getTitle());
        basicInformation.setMobileNumber(basicInformationDto.getMobileNumber());
        basicInformation.setEmail(basicInformationDto.getEmail());
        basicInformation.setLinkedinUrl(basicInformationDto.getLinkedinUrl());

        BasicInformation updateBasicInformation = basicInformationRepo.save(basicInformation);
        return modelMapper.map(updateBasicInformation, BasicInformationDto.class);
    }

    @Override
    public void deleteBasicInformation(Short basicInfoId) {

        BasicInformation basicInformation = basicInformationRepo.findById(basicInfoId)
                .orElseThrow(()-> new ResourceNotFoundException("Basic Information", "Id", basicInfoId));
        basicInformationRepo.delete(basicInformation);
    }

    @Override
    public BasicInformationDto getBasicInformationById(Short basicInfoId) {

        BasicInformation basicInformation = basicInformationRepo.findById(basicInfoId)
                .orElseThrow(()-> new ResourceNotFoundException("Basic Information ","Id",basicInfoId));
        return modelMapper.map(basicInformation,BasicInformationDto.class);
    }

    @Override
    public List<BasicInformationDto> getAllBasicInfo() {

        List<BasicInformation> allBasicInformation = basicInformationRepo.findAll();
        List<BasicInformationDto> allBasicInformationDto = allBasicInformation.stream()
                .map((basicInfo)-> modelMapper.map(basicInfo,BasicInformationDto.class)).toList();
        return allBasicInformationDto;
    }


}
