package com.cv.cvgenarator.repo;

import com.cv.cvgenarator.entity.BasicInformation;
import com.cv.cvgenarator.entity.ExperienceInformation;
import com.cv.cvgenarator.entity.ProjectInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectInformationRepo extends JpaRepository<ProjectInformation, Short> {


    List<ProjectInformation> findByExperienceInformation_BasicInformation_Id(Short basicInfoId);
    List<ProjectInformation> findProjectInformationByExperienceInformationId(Short experienceInfoId);
}
