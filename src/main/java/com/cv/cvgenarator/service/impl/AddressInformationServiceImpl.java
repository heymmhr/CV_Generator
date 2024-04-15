package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.AddressInformationDto;
import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.IdNameDto;
import com.cv.cvgenarator.dto.LocalLevelDto;
import com.cv.cvgenarator.entity.*;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.AddressInformationRepo;
import com.cv.cvgenarator.repo.BasicInformationRepo;
import com.cv.cvgenarator.repo.LocalLevelRepo;
import com.cv.cvgenarator.service.AddressInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressInformationServiceImpl implements AddressInformationService {

    private final AddressInformationRepo addressInformationRepo;

    private final ModelMapper modelMapper;

    private final BasicInformationRepo basicInformationRepo;

    private final LocalLevelRepo localLevelRepo;

    public AddressInformationServiceImpl(AddressInformationRepo addressInformationRepo, ModelMapper modelMapper, BasicInformationRepo basicInformationRepo, LocalLevelRepo localLevelRepo) {
        this.addressInformationRepo = addressInformationRepo;
        this.modelMapper = modelMapper;
        this.basicInformationRepo = basicInformationRepo;
        this.localLevelRepo = localLevelRepo;
    }


    @Override
    public AddressInformationDto createAddressInformation(AddressInformationDto addressInformationDto, Short basicId, Short localLevelId) {
        AddressInformation addressInformation = dtoToAddressInfo(addressInformationDto, basicId, localLevelId);
        AddressInformation createAddress = addressInformationRepo.save(addressInformation);
        return addressInfoToDto(createAddress, basicId, localLevelId);
    }

    @Override
    public AddressInformationDto updateAddressInformation(AddressInformationDto addressInformationDto, Short addressId) {

        AddressInformation addressInformation = addressInformationRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address Information", "Id", addressId));

        addressInformation.setAddressType(addressInformation.getAddressType());
        addressInformation.setBasicInformation(addressInformation.getBasicInformation());
        addressInformation.setLocalLevel(addressInformation.getLocalLevel());

        AddressInformation updateAddressInformation = addressInformationRepo.save(addressInformation);
        return modelMapper.map(updateAddressInformation, AddressInformationDto.class);

    }

    @Override
    public void deleteAddressInformation(Short addressId) {

        AddressInformation addressInformation = addressInformationRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information", "Id", addressId));
        addressInformationRepo.delete(addressInformation);

    }

    @Override
    public AddressInformationDto getAddressInformationById(Short addressId) {

        AddressInformation addressInformation = addressInformationRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information ", "Id", addressId));
        return modelMapper.map(addressInformation, AddressInformationDto.class);
    }

    @Override
    public List<AddressInformationDto> getAllAddress() {

        List<AddressInformation> allAddressInformation = addressInformationRepo.findAll();
        List<AddressInformationDto> allAddressInformationDto = allAddressInformation.stream()
                .map((basicInfo) -> modelMapper.map(basicInfo, AddressInformationDto.class)).toList();
        return allAddressInformationDto;
    }

    public AddressInformation dtoToAddressInfo(AddressInformationDto addressInformationDto, Short basicId, Short localLevelId) {

        BasicInformation basicInformation = basicInformationRepo.findById(basicId)
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information", "Id", basicId));

        LocalLevel localLevel = localLevelRepo.findById(localLevelId)

                .orElseThrow(() -> new ResourceNotFoundException("Local Level", "Id", localLevelId));

        AddressInformation addressInformation = new AddressInformation();
        addressInformation.setId(addressInformationDto.getId());
        addressInformation.setBasicInformation(basicInformation);
        addressInformation.setAddressType(addressInformationDto.getAddressType());
        addressInformation.setLocalLevel(localLevel);
        return addressInformation;
    }

    public AddressInformationDto addressInfoToDto(AddressInformation addressInformation, Short basicId, Short localLevelId) {

        BasicInformationDto basicInformationDto = new BasicInformationDto();

        basicInformationDto.setId(addressInformation.getBasicInformation().getId());
        basicInformationDto.setFirstName(addressInformation.getBasicInformation().getFirstName());
        basicInformationDto.setMiddleName(addressInformation.getBasicInformation().getMiddleName());
        basicInformationDto.setLastName(addressInformation.getBasicInformation().getLastName());
        basicInformationDto.setBackground(addressInformation.getBasicInformation().getBackground());
        basicInformationDto.setTitle(addressInformation.getBasicInformation().getTitle());
        basicInformationDto.setMobileNumber(addressInformation.getBasicInformation().getMobileNumber());
        basicInformationDto.setEmail(addressInformation.getBasicInformation().getEmail());
        basicInformationDto.setLinkedinUrl(addressInformation.getBasicInformation().getLinkedinUrl());
        basicInformationDto.setProfileImage(addressInformation.getBasicInformation().getProfileImage());

        LocalLevelDto localLevelDto = new LocalLevelDto();
        localLevelDto.setId(addressInformation.getLocalLevel().getId());
        localLevelDto.setName(addressInformation.getLocalLevel().getName());
        localLevelDto.setNameNepali(addressInformation.getLocalLevel().getNameNepali());
        localLevelDto.setCode(addressInformation.getLocalLevel().getCode());
        localLevelDto.setTotalWardCount(addressInformation.getLocalLevel().getTotalWardCount());

        AddressInformationDto addressInformationDto = new AddressInformationDto();
        addressInformationDto.setId(addressInformation.getId());
        addressInformationDto.setBasicInformation(basicInformationDto);
        addressInformationDto.setAddressType(addressInformation.getAddressType());
        addressInformationDto.setLocalLevel(localLevelDto);
        addressInformationDto.setLocal(getLocal(addressInformation.getLocalLevel()));

        return addressInformationDto;
    }

    private IdNameDto getLocal(LocalLevel entity){
        if(entity == null)
            return null;

        return IdNameDto.builder().id(entity.getId()).name(entity.getName()).build();
    }

    private IdNameDto getDistrict(District entity){
        return null;
    }
}
