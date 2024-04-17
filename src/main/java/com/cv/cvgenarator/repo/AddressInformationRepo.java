package com.cv.cvgenarator.repo;

import com.cv.cvgenarator.entity.AddressInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressInformationRepo extends JpaRepository<AddressInformation,Short> {

    List<AddressInformation> findAddressInformationByBasicInformationId(Short basicInfoId);
}
