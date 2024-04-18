package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.CountryDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController extends BaseController{

    private final CountryService countryService;
    private final CustomMessageSource customMessageSource;

    public CountryController(CountryService countryService, CustomMessageSource customMessageSource) {
        this.countryService = countryService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.COUNTRY;
    }

    //create
    @PostMapping("/save")
    public ResponseEntity<ResponseDto> createCountry(@RequestBody CountryDto countryDto) {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), countryService
                .createCountry(countryDto)), HttpStatus.OK);
    }

    // update

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCountry(@RequestBody CountryDto countryDto, @PathVariable Short id) {

        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_UPDATE, customMessageSource
                        .get(MessageCodeConstant.COUNTRY)), countryService.updateCountry(countryDto, id)), HttpStatus.OK);
    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCountry(@PathVariable Short id) {

        countryService.deleteCountry(id);
        return ResponseEntity.ok(successResponse(customMessageSource
                .get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));
    }

    // get

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCountryId(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET, customMessageSource
                        .get(MessageCodeConstant.COUNTRY)), countryService.getCountryById(id)), HttpStatus.OK);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllCountry() {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), countryService.getAllCountries()), HttpStatus.OK);
    }
}
