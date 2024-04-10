package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.District;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class LocalLevelDto {

    private Short id;

    private String name;

    private String nameNepali;

    private String code;

    private DistrictDto district;

    private Integer totalWardCount;
}
