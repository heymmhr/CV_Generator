package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.UserDto;
import com.cv.cvgenarator.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

     UserDto registerNewUser(UserDto user);
     UserDto createUser(UserDto user);
     UserDto updateUser(UserDto user, Short userId);

     void deleteUser(Short userId);

     UserDto getUserById(Short userId);

     List<UserDto> getAllUsers();

}
