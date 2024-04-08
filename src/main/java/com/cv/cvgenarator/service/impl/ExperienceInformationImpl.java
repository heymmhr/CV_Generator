package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.ExperienceInformation;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.BasicInformationRepo;
import com.cv.cvgenarator.repo.ExperienceInformationRepo;
import com.cv.cvgenarator.service.ExperienceInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ExperienceInformationImpl implements ExperienceInformationService {

    private final ExperienceInformationRepo experienceInformationRepo;
    private final ModelMapper modelMapper;
    private final BasicInformationRepo basicInformationRepo;

    public ExperienceInformationImpl(ExperienceInformationRepo experienceInformationRepo, ModelMapper modelMapper, BasicInformationRepo basicInformationRepo) {
        this.experienceInformationRepo = experienceInformationRepo;
        this.modelMapper = modelMapper;
        this.basicInformationRepo = basicInformationRepo;
    }


    @Override
    public ExperienceInformationDto createExperienceInformation(ExperienceInformationDto experienceInformationDto) {

        ExperienceInformation experienceInformation1 = new ExperienceInformation();

        BasicInformation basicInformation = basicInformationRepo
                .findById(experienceInformationDto.getBasicInformation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information", "Id", experienceInformationDto.getBasicInformation().getId()));
        experienceInformation1.setBasicInformation(basicInformation);

        ExperienceInformation experienceInformation = modelMapper.map(experienceInformationDto, ExperienceInformation.class);
        ExperienceInformation createdExperienceInfo = experienceInformationRepo.save(experienceInformation);
        return modelMapper.map(createdExperienceInfo, ExperienceInformationDto.class);
    }

    @Override
    public ExperienceInformationDto updateExperienceInformation(ExperienceInformationDto experienceInformationDto, Short experienceInfoId) {

        ExperienceInformation experienceInformation = experienceInformationRepo
                .findById(experienceInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience Information ", "Id", experienceInfoId));

        experienceInformation.setCompanyName(experienceInformationDto.getCompanyName());
        experienceInformation.setCompanyAddress(experienceInformationDto.getCompanyAddress());
        experienceInformation.setCompanyContact(experienceInformationDto.getCompanyContact());
        experienceInformation.setFromDate(experienceInformationDto.getFromDate());
        experienceInformation.setToDate(experienceInformationDto.getToDate());
        experienceInformation.setToPresent(experienceInformationDto.getToPresent());

        ExperienceInformation updatedExperienceInfo = experienceInformationRepo.save(experienceInformation);
        return modelMapper.map(updatedExperienceInfo, ExperienceInformationDto.class);
    }


    @Override
    public void deleteExperienceInformation(Short experienceInfoId) {

        ExperienceInformation experienceInformation = experienceInformationRepo.findById(experienceInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information", "Id", experienceInfoId));
        experienceInformationRepo.delete(experienceInformation);
    }

    @Override
    public ExperienceInformationDto getExperienceInfoById(Short experienceInfoId) {

        ExperienceInformation experienceInformation = experienceInformationRepo.findById(experienceInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information ", "Id", experienceInfoId));
        return modelMapper.map(experienceInformation, ExperienceInformationDto.class);
    }

    @Override
    public List<ExperienceInformationDto> getAllExperienceInformation() {

        List<ExperienceInformation> allExperienceInformation = experienceInformationRepo.findAll();
        List<ExperienceInformationDto> allExperienceInformationDto = allExperienceInformation.stream()
                .map((experienceinfo) -> modelMapper.map(experienceinfo, ExperienceInformationDto.class)).toList();
        return allExperienceInformationDto;
    }
}
