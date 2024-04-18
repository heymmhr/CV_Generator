package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.District;
import com.cv.cvgenarator.entity.LocalLevel;
import com.cv.cvgenarator.entity.Province;
import com.cv.cvgenarator.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressInformationDto {

    private Short id;

    @NotNull(message = "Address type is required")
    private AddressType addressType;


    private Short basicInformationId;

    @NotNull(message = "Province ID is required")
    private Short provinceId;

    @NotNull(message = "Country ID is required")
    private Short countryId;

    @NotNull(message = "District ID is required")
    private Short districtId;

    @NotNull(message = "Locallevel ID is required")
    private Short localLevelId;

    private IdNameDto country;
    private IdNameDto province;
    private IdNameDto district;
    private IdNameDto local;
}
