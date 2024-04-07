package com.cv.cvgenarator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DegreeName {

    SCHOOL,
    HIGH_SCHOOL,
    BACHELORS,
    MASTERS,
    PHD;

    private Integer key;
    private String value;


    public static DegreeName findByKey(Integer key) throws ChangeSetPersister.NotFoundException {
        for (DegreeName status : DegreeName.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public static DegreeName findByValue(String value) throws ChangeSetPersister.NotFoundException {
        for (DegreeName status : DegreeName.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new ChangeSetPersister.NotFoundException();

    }

}
