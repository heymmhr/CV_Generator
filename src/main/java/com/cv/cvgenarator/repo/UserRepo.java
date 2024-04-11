package com.cv.cvgenarator.repo;

import com.cv.cvgenarator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Short> {

    Optional<User> findByEmail(String email);

}
