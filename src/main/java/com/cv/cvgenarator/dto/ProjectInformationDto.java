package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.ExperienceInformation;
import com.cv.cvgenarator.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectInformationDto {

    private Short id;

    private Short experienceInformationId;

    @NotNull(message = "Project Name must not be empty")
    @Size(message = "Project name must not exceed 100", max = 100)
    private String projectName;

    @NotNull(message = "Project status must not be empty")
    private ProjectStatus projectStatus;

    @NotNull(message = "Project Role must not be empty")
    @Size(message = "Project Role must not exceed more than 50", max = 50)
    private String projectRole;

    @NotNull(message = "Project Description must not be empty")
    private String projectDescription;

    @NotNull(message = "TechStack must not be empty")
    private String techStack;

    @Size(message = "URL must not exceed 200", max = 200)
    private String projectUrl;
}
