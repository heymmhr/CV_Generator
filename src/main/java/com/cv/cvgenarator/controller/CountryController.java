package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.CountryDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    //create
    @PostMapping("/save")
    public ResponseEntity<ResponseDto> createCountry(@RequestBody CountryDto countryDto) {

        CountryDto country = countryService.createCountry(countryDto);
        return ResponseEntity.ok
                (new ResponseDto("Country Created successfully!!: देशहरू सफलतापूर्वक सिर्जना गरियो |", true, country));
    }

    // update

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCountry(@RequestBody CountryDto countryDto, @PathVariable Short id) {

        CountryDto updateCountry = countryService.updateCountry(countryDto, id);
        return ResponseEntity.ok
                (new ResponseDto("Country Updated Successfully!! : देशहरू सफलतापूर्वक अद्यावधिक गरियो |", true, updateCountry));
    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCountry(@PathVariable Short id) {

        countryService.deleteCountry(id);
        return ResponseEntity.ok
                (new ResponseDto("Country Deleted Successfully!! : देशहरू सफलतापूर्वक मेटियो", true, null));
    }

    // get

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCountryId(@PathVariable Short id) {

        CountryDto countryById = countryService.getCountryById(id);
        return ResponseEntity.ok
                (new ResponseDto("Country extracted by id!! : id द्वारा निकालिएको देशहरू |", true, countryById));
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllCountry() {

        List<CountryDto> allCountry = countryService.getAllCountries();
        return ResponseEntity.ok
                (new ResponseDto("All Country extracted!! : सबै देशहरू निकालियो |", true, allCountry));
    }
}
