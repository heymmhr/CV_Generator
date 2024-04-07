package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.ProjectInformation;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class ExperienceInformationDto {
    private Short id;

    private BasicInformation basicInformation;

    private String companyName;

    private String companyAddress;

    private String companyContact;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Boolean toPresent;

    private List<ProjectInformation> projectInformationList;
}
