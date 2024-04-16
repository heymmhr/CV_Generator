package com.cv.cvgenarator.repo;

import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.EducationInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationInformationRepo extends JpaRepository<EducationInformation,Short> {
    List<EducationInformation> findEducationInformationByBasicInformation(BasicInformation basicInformation);
}
