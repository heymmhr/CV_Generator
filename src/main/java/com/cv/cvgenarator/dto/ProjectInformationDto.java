package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.ExperienceInformation;
import com.cv.cvgenarator.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class ProjectInformationDto {

    private Short id;

    private ExperienceInformationDto experienceInformation;

    private String projectName;

    private ProjectStatus projectStatus;

    private String projectRole;

    private String projectDescription;

    private String techStack;

    private String projectUrl;
}
