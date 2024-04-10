package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.dto.AddressInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.AddressInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address-info")
public class AddressInformationController {

    private final AddressInformationService addressInformationService;

    public AddressInformationController(AddressInformationService addressInformationService) {
        this.addressInformationService = addressInformationService;
    }

    //create
    @PostMapping("/basic-info/{basic-info-id}/{local-level-id}/address")
    public ResponseEntity<ResponseDto> createAddressInfo(@RequestBody AddressInformationDto addressInformationDto,
                                                         @PathVariable("basic-info-id") Short basicInfoId,
                                                         @PathVariable ("local-level-id") Short localLevelId) {

        AddressInformationDto addressInformation = addressInformationService.createAddressInformation(addressInformationDto, basicInfoId, localLevelId);
        return ResponseEntity.ok(new ResponseDto("Address Information Created successfully!!: ठेगाना जानकारी सफलतापूर्वक सिर्जना गरियो |", true, addressInformation));
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
        return ResponseEntity.ok(new ResponseDto("Address Information Deleted Successfully!! : ठेगाना जानकारी सफलतापूर्वक मेटियो", true, null));
    }

    // get

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getAddressInfoById(@PathVariable Short id) {

        AddressInformationDto addressInformationById = addressInformationService.getAddressInformationById(id);
        return ResponseEntity.ok(new ResponseDto("Address Information extracted by id!! : id द्वारा निकालिएको ठेगाना जानकारी |", true, addressInformationById));
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllAddressInfo() {

        List<AddressInformationDto> allAddressInfo = addressInformationService.getAllAddress();
        return ResponseEntity.ok(new ResponseDto("All Address Information extracted!! : सबै आधारभूत जानकारी निकालियो |", true, allAddressInfo));
    }
}
