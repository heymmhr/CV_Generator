package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.AddressInformationDto;
import com.cv.cvgenarator.entity.AddressInformation;

import java.util.List;

public interface AddressInformationService {

    // create
    AddressInformationDto createAddressInformation(AddressInformationDto addressInformationDto);

    //update
    AddressInformationDto updateAddressInformation(AddressInformationDto addressInformationDto, Short addressId);

    //delete
    void deleteAddressInformation(Short addressId);

    // get
    AddressInformationDto getAddressInformation(Short addressId);

    // get all
    List<AddressInformationDto> getAddresses();
}
