package com.cv.cvgenarator.repo;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.EducationInformation;
import com.cv.cvgenarator.entity.ExperienceInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceInformationRepo extends JpaRepository<ExperienceInformation,Short> {

    List<ExperienceInformation> findExperienceInformationByBasicInformationId(Short basicInfoId);
}
