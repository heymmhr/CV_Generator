package com.cv.cvgenarator.enums;

import com.cv.cvgenarator.dto.EnumDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



public enum ProjectStatus {

    LIVE("LIVE"),
    UAT("UAT"),
    DEVELOPMENT("DEVELOPMENT");

    private final String key;

    ProjectStatus(String key) {
        this.key = key;
    }


    //TODO dropdown list api
    public static List<EnumDto> getProjectStatusList(){
        return Arrays.stream(ProjectStatus.values()).map(projectStatus -> new EnumDto(projectStatus.key)).collect(Collectors.toList());
    }
}
