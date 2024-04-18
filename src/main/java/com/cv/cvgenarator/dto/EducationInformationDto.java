package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.enums.DegreeName;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationInformationDto {

    private Short id;

    private Short basicInformationId;

    @NotBlank(message = "Institution name must not be empty")
    @Size(message = "Values must not exceed 200", max = 200, min = 3)
    private String institutionName;

    @Size(message = "Maximum value for Institution address is 200  ", max = 200)
    @NotBlank(message = "Institution address must not be empty")
    private String institutionAddress;

    @NotBlank(message = "Institution contact must not be empty")
    @Size(message = "Values must not exceed 10", max = 10)
    private String institutionContact;

    @NotNull(message = "From date must not be null")
    private LocalDate fromDate;

    private LocalDate toDate;

    private Boolean toPresent;

    @NotNull(message = "Degree name must not be null")
    private DegreeName degreeName;

    @NotBlank(message = "Education description must not be empty")
    @Size(message = "Values must not exceed 100", max = 100)
    private String educationDescription;
}
