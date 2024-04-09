package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.AddressInformationDto;
import com.cv.cvgenarator.entity.AddressInformation;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.AddressInformationRepo;
import com.cv.cvgenarator.service.AddressInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressInformationServiceImpl implements AddressInformationService {

    private final AddressInformationRepo addressInformationRepo;

    private final ModelMapper modelMapper;

    public AddressInformationServiceImpl(AddressInformationRepo addressInformationRepo, ModelMapper modelMapper) {
        this.addressInformationRepo = addressInformationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressInformationDto createAddressInformation(AddressInformationDto addressInformationDto) {

        AddressInformation addressInformation = modelMapper.map(addressInformationDto,AddressInformation.class);
        AddressInformation createdAddressInfo = addressInformationRepo.save(addressInformation);
        return modelMapper.map(createdAddressInfo, AddressInformationDto.class);

    }

    @Override
    public AddressInformationDto updateAddressInformation(AddressInformationDto addressInformationDto, Short addressId) {

        AddressInformation addressInformation = addressInformationRepo.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address Information", "Id", addressId));

        addressInformation.setAddressType(addressInformation.getAddressType());
        addressInformation.setBasicInformation(addressInformation.getBasicInformation());
        addressInformation.setLocalLevel(addressInformation.getLocalLevel());

        AddressInformation updateAddressInformation = addressInformationRepo.save(addressInformation);
        return modelMapper.map(updateAddressInformation, AddressInformationDto.class);
    }

    @Override
    public void deleteAddressInformation(Short addressId) {

        AddressInformation addressInformation = addressInformationRepo.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Basic Information", "Id", addressId));
        addressInformationRepo.delete(addressInformation);

    }

    @Override
    public AddressInformationDto getAddressInformationById(Short addressId) {

        AddressInformation addressInformation = addressInformationRepo.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Basic Information ","Id",addressId));
        return modelMapper.map(addressInformation,AddressInformationDto.class);
    }

    @Override
    public List<AddressInformationDto> getAllAddress() {

        List<AddressInformation> allAddressInformation = addressInformationRepo.findAll();
        List<AddressInformationDto> allAddressInformationDto = allAddressInformation.stream()
                .map((basicInfo)-> modelMapper.map(basicInfo,AddressInformationDto.class)).toList();
        return allAddressInformationDto;
    }
}
