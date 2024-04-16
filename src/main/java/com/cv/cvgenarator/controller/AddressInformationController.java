package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.AddressInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.AddressInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address-info")
public class AddressInformationController extends BaseController{

    private final AddressInformationService addressInformationService;
    private final CustomMessageSource customMessageSource;

    public AddressInformationController(AddressInformationService addressInformationService, CustomMessageSource customMessageSource) {
        this.addressInformationService = addressInformationService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.ADDRESS_INFORMATION;
    }

    //create
    @PostMapping("/basic-info/{basic-info-id}/{local-level-id}/address")
    public ResponseEntity<ResponseDto> createAddressInfo(@RequestBody AddressInformationDto addressInformationDto,
                                                         @PathVariable("basic-info-id") Short basicInfoId,
                                                         @PathVariable ("local-level-id") Short localLevelId) {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), addressInformationService
                .createAddressInformation(addressInformationDto,basicInfoId,localLevelId)), HttpStatus.OK);
    }

    // update

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateAddressInfo(@RequestBody AddressInformationDto addressInformationDto, @PathVariable Short id) {

        AddressInformationDto updateAddressInfo = addressInformationService.updateAddressInformation(addressInformationDto, id);
        return ResponseEntity.ok(new ResponseDto("Address Information Updated Successfully!! : ठेगाना जानकारी सफलतापूर्वक अद्यावधिक गरियो |", true, updateAddressInfo));
    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteAddressInfo(@PathVariable Short id) {

        addressInformationService.deleteAddressInformation(id);
        return ResponseEntity.ok(
                successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));
    }

    // get

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getAddressInfoById(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET, customMessageSource.get(MessageCodeConstant.ADDRESS_INFORMATION)), addressInformationService
                .getAddressInformationById(id)), HttpStatus.OK);

    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllAddressInfo() {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), addressInformationService.getAllAddress()), HttpStatus.OK);
    }
}
