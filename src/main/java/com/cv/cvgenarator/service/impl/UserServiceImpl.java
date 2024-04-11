package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.UserDto;
import com.cv.cvgenarator.entity.User;
import com.cv.cvgenarator.exceptions.ResourceNotFoundException;
import com.cv.cvgenarator.repo.UserRepo;
import com.cv.cvgenarator.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user1 = this.modelMapper.map(userDto,User.class);       //convert userDto to user

        user1.setPassword(this.passwordEncoder.encode(user1.getPassword()));

        User newUser = this.userRepo.save(user1);

        return this.modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto user) {
        User user1 = this.dtoToUser(user);
        user.setPassword(user1.getPassword());
        User savedUser = this.userRepo.save(user1);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, Short userId) {

        User user1 = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());

        User updatedUser = this.userRepo.save(user1);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public void deleteUser(Short userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        this.userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(Short userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();

        List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());

        return userDtos;
    }

    private User dtoToUser(UserDto userDto){

        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToDto(User user){

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
