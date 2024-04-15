package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.District;
import com.cv.cvgenarator.entity.LocalLevel;
import com.cv.cvgenarator.entity.Province;
import com.cv.cvgenarator.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressInformationDto {

    private Short id;

    private AddressType addressType;

    private BasicInformationDto basicInformation;

   /* private Province province;

    private District district;*/

    private LocalLevelDto localLevel;

    private IdNameDto country;
    private IdNameDto province;
    private IdNameDto district;
    private IdNameDto local;
}
