package com.cv.cvgenarator.enums;

import com.cv.cvgenarator.dto.EnumDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AddressType {

    PERMANENT("PERMANENT"),
    TEMPORARY("TEMPORARY");

    private final String key;

    AddressType(String key) {
        this.key = key;
    }


    public static List<EnumDto> getAddressTypeList() {
        return Arrays.stream(AddressType.values()).map(addressType -> new EnumDto(addressType.key)).collect(Collectors.toList());
    }
}
