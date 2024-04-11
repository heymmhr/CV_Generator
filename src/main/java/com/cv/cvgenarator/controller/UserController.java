package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.dto.UserDto;
import com.cv.cvgenarator.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //create
    @PostMapping("/save")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) {

        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(new ResponseDto
                ("User Created successfully!!: प्रान्त सफलतापूर्वक सिर्जना गरियो |", true, createdUser));

    }

    //update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto,
                                                  @PathVariable Short id) {

        UserDto updateUser = userService.updateUser(userDto, id);
        return updateUser != null ?
                ResponseEntity.ok(new ResponseDto
                        ("User Updated Successfully!! : प्रान्त सफलतापूर्वक अद्यावधिक गरियो |", true, updateUser)) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Short id) {

        userService.deleteUser(id);
        return ResponseEntity.ok(new
                ResponseDto("User Deleted Successfully!! : प्रान्त सफलतापूर्वक मेटियो", true, null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable Short id) {

        UserDto userById = userService.getUserById(id);

        return userById != null ?
                ResponseEntity.ok(new ResponseDto
                        ("User extracted by id!! : id द्वारा निकालिएको प्रान्त |", true, userById)) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllProvince() {

        List<UserDto> allProvince = userService.getAllUsers();
        return ResponseEntity.ok
                (new ResponseDto
                        ("All Users extracted!! : सबै प्रान्त निकालियो |", true, allProvince));
    }


}
