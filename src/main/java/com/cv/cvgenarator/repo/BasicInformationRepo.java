package com.cv.cvgenarator.repo;

import com.cv.cvgenarator.entity.BasicInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicInformationRepo extends JpaRepository<BasicInformation,Short> {
}
