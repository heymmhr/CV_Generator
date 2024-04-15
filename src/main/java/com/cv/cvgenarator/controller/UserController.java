package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.dto.UserDto;
import com.cv.cvgenarator.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final CustomMessageSource customMessageSource;


    public UserController(UserService userService, CustomMessageSource customMessageSource) {
        this.userService = userService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.USER;
    }


    //create
    @PostMapping("/save")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), createUser(userDto)), HttpStatus.OK);

    }

    //update by id

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto,
                                                  @PathVariable Short id) {

      /*  UserDto updateUser = userService.updateUser(userDto, id);
        return updateUser != null ?
                ResponseEntity.ok(new ResponseDto("User Updated Successfully!! : प्रान्त सफलतापूर्वक अद्यावधिक गरियो |", true, updateUser)) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);*/

        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_UPDATE,
                        customMessageSource.get(MessageCodeConstant.USER)), userService.updateUser(userDto, id)), HttpStatus.OK);

    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Short id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));
    }

    // get by id

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET,
                        customMessageSource.get(MessageCodeConstant.USER)), userService.getUserById(id)), HttpStatus.OK);

    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllProvince() {

        // List<UserDto> allProvince = userService.getAllUsers();

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), userService.getAllUsers()), HttpStatus.OK);

        //return ResponseEntity.ok(new ResponseDto("All Users extracted!! : सबै प्रान्त निकालियो |", true, allProvince));
    }


}
