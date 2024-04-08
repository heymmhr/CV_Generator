package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.BasicInformation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExperienceInformationDto {
    private Short id;

    private BasicInformation basicInformation;

    private String companyName;

    private String companyAddress;

    private String companyContact;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Boolean toPresent;

    /*private List<ProjectInformation> projectInformation;*/


}
