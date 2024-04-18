package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.ExperienceInformation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ExperienceInformationDto {
    private Short id;

    private Short basicInformationId;

    @NotBlank(message = "Company Name Must not be empty")
    @Size(message = "Values must not exceed 200", max = 200)
    private String companyName;

    @NotBlank(message = "Company Address must not be empty")
    @Size(message = "Values must not exceed 200", max = 200)
    private String companyAddress;

    @NotBlank(message = "company Contact number must not be empty")
    @Size(message = "Values must not exceed 10", max = 10)
    private String companyContact;

    @NotNull(message = "from date must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    private Boolean toPresent;

    /*private List<ProjectInformation> projectInformation;*/




}
