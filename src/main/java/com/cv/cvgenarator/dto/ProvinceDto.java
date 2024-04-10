package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.Country;
import com.cv.cvgenarator.entity.District;
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
public class ProvinceDto {

    private Short id;

    private String name;

    private String nameNepali;

    private String code;

    private CountryDto country;
}
