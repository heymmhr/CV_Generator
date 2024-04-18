package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.LocalLevel;
import com.cv.cvgenarator.entity.Province;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictDto {

    private Short id;

    private String name;

    private String nameNepali;

    private String code;

    private ProvinceDto province;

}
