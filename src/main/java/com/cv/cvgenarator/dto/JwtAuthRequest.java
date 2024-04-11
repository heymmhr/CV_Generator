package com.cv.cvgenarator.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthRequest {

    private String username;

    private String password;
}
