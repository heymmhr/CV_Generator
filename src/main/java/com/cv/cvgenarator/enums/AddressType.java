package com.cv.cvgenarator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AddressType {

    ADDRESS_TYPE,
    PERMANENT,
    TEMPORARY;

    private Integer key;
    private String value;


    public static AddressType findByKey(Integer key) throws ChangeSetPersister.NotFoundException {
        for (AddressType status : AddressType.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public static AddressType findByValue(String value) throws ChangeSetPersister.NotFoundException {
        for (AddressType status : AddressType.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new ChangeSetPersister.NotFoundException();

    }
}
