package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.CountryDto;
import com.cv.cvgenarator.entity.Country;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.CountryRepo;
import com.cv.cvgenarator.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepo countryRepo;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepo countryRepo, ModelMapper modelMapper) {
        this.countryRepo = countryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountryDto createCountry(CountryDto countryDto) {
        Country country = modelMapper.map(countryDto, Country.class);
        Country createdCountry = countryRepo.save(country);
        return modelMapper.map(createdCountry, CountryDto.class);
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto, Short countryId) {
        Country country = countryRepo.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "Id", countryId));

        country.setName(countryDto.getName());
        country.setNameNepali(countryDto.getNameNepali());

        Country updateCountry = countryRepo.save(country);
        return modelMapper.map(updateCountry, CountryDto.class);
    }

    @Override
    public void deleteCountry(Short countryId) {

        Country country = countryRepo.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "Id", countryId));
        countryRepo.delete(country);
    }

    @Override
    public CountryDto getCountryById(Short countryId) {
        Country country = countryRepo.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country Information ", "Id", countryId));
        return modelMapper.map(country, CountryDto.class);
    }

    @Override
    public List<CountryDto> getAllCountries() {
        List<Country> allCountry = countryRepo.findAll();
        List<CountryDto> allCountryDto = allCountry.stream()
                .map((basicInfo) -> modelMapper.map(basicInfo, CountryDto.class)).toList();
        return allCountryDto;
    }
}
