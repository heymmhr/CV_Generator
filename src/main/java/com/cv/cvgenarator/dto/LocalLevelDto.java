package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.District;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalLevelDto {

    private Short id;

    private String name;

    private String nameNepali;

    private String code;

    private District district;

    private Integer totalWardCount;
}
