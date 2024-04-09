package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.enums.DegreeName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter

public class EducationInformationDto {

    private Short id;

    private BasicInformationDto basicInformation;

    private String institutionName;

    private String institutionAddress;

    private String institutionContact;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Boolean toPresent;

    private DegreeName degreeName;

    private String educationDescription;
}
