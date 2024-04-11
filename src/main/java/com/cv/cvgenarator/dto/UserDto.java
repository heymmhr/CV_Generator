package com.cv.cvgenarator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Short id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters")
    private String name;


    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be minimum of 3 chars and maximum of 10 chars !!")
    private String password;

    @Email(message = "Email address is not valid")
    private String email;
}
