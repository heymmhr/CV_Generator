package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.ExperienceInformation;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicInformationDto {

    private Short id;

    @NotEmpty(message = "First Name Is Required")
    @Size(message = "Minimum and maximum letters of firstname is 2 and 100 respectively", min = 2, max = 100)
    private String firstName;

    private String middleName;

    @NotEmpty(message = "Last Name Is Required")
    @Size(message = "Minimum and maximum letters of Last Name is 2 and 100 respectively", min = 2, max = 100)
    private String lastName;

    @NotEmpty(message = "Background Is Required")
    private String background;

    @NotEmpty(message = "Title Is Required")
    private String title;

    @NotEmpty(message = "Mobile Number Is Required")
    @Size(message = "Minimum and maximum numbers mobileNumber is 10 and 10 respectively", min = 10, max = 10)
    private String mobileNumber;

    @Email(message = "Invalid Email Address")
    @NotNull(message = "Email must not be null")
    @NotEmpty(message = "Email must not be empty")
    @Size(message = "Maximum value of email 100 ", max = 100)
    private String email;

    @NotNull(message = "LinkedIn URL must not be null")
    @Size(message = "Maximum value of url is 200 ", max = 200)
    private String linkedinUrl;

    private String profileImage;

}
