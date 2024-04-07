package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.Province;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

    private Short id;

    private String nameNepali;

    private String name;

    private List<Province> provinceList;
}
