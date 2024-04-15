package com.cv.cvgenarator.dto;

import lombok.*;

@Getter
@Setter

public class JwtAuthRequest {

    private String username;

    private String password;
}
