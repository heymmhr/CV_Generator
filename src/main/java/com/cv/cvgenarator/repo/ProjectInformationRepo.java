package com.cv.cvgenarator.repo;

import com.cv.cvgenarator.entity.ProjectInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectInformationRepo extends JpaRepository<ProjectInformation, Short> {
}
