package com.cv.cvgenarator.enums;

import com.cv.cvgenarator.dto.EnumDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public enum DegreeName {

    SCHOOL("SCHOOL"),
    HIGH_SCHOOL("HIGH_SCHOOL"),
    BACHELORS("BACHELORS"),
    MASTERS("MASTERS"),
    PHD("PHD");

    private final String key;

    DegreeName(String key) {
        this.key = key;
    }

    public static List<EnumDto> getDegreeName() {
        return Arrays.stream(DegreeName.values()).map(degreeName -> new EnumDto(degreeName.key)).collect(Collectors.toList());
    }

}
