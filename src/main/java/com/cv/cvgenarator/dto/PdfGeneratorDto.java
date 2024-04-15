package com.cv.cvgenarator.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PdfGeneratorDto {

    private BasicInformationDto basicInformationDto;

    private ExperienceInformationDto experienceInformationDto;

    private EducationInformationDto educationInformationDto;

    private ProjectInformationDto projectInformationDto;

    private AddressInformationDto addressInformationDto;

    private CountryDto countryDto;

    private DistrictDto districtDto;

    private ProvinceDto provinceDto;

    private LocalLevelDto localLevelDto;
}
