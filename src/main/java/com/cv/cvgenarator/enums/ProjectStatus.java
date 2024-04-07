package com.cv.cvgenarator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.crossstore.ChangeSetPersister;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public enum ProjectStatus {

    LIVE,
    UAT,
    DEVELOPMENT;

    private Integer key;
    private String value;


    public static ProjectStatus findByKey(Integer key) throws ChangeSetPersister.NotFoundException {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public static ProjectStatus findByValue(String value) throws ChangeSetPersister.NotFoundException {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new ChangeSetPersister.NotFoundException();

    }
}
