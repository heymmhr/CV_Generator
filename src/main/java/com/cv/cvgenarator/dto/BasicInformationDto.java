package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.ExperienceInformation;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BasicInformationDto {

    private Short id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String background;

    private String title;

    private String mobileNumber;

    private String email;

    private String linkedinUrl;

    private String profileImage;

}
