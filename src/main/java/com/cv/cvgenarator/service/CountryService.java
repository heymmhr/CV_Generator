package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.CountryDto;

import java.util.List;

public interface CountryService {

    // create
    CountryDto createCountry(CountryDto countryDto);

    //update
    CountryDto updateCountry(CountryDto countryDto, Short countryId);

    //delete
    void deleteCountry(Short countryId);

    // get
    CountryDto getCountryById(Short countryId);

    // get all
    List<CountryDto> getAllCountries();
}
