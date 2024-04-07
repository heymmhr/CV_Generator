package com.cv.cvgenarator.dto;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.District;
import com.cv.cvgenarator.entity.LocalLevel;
import com.cv.cvgenarator.entity.Province;
import com.cv.cvgenarator.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressInformationDto {

    private Short id;

    private AddressType addressType;

    private BasicInformation basicInformation;

    private Province province;

    private District district;

    private LocalLevel localLevel;
}
