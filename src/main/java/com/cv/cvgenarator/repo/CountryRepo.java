package com.cv.cvgenarator.repo;

import com.cv.cvgenarator.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country,Short> {
}
