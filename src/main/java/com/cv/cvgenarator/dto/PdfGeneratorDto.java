package com.cv.cvgenarator.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
