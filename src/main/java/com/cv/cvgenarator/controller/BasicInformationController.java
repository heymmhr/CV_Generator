package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.constants.MessageCodeConstant;
import com.cv.cvgenarator.constants.MessageConstant;
import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.ResponseDto;
import com.cv.cvgenarator.service.BasicInformationService;
import com.cv.cvgenarator.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/api/basic-info")
public class BasicInformationController extends BaseController{

    private final BasicInformationService basicInformationService;

    private final FileService fileService;
    private final CustomMessageSource customMessageSource;

    @Value("${project.image}")
    private String path;

    public BasicInformationController(BasicInformationService basicInformationService, FileService fileService, CustomMessageSource customMessageSource) {
        this.basicInformationService = basicInformationService;
        this.fileService = fileService;
        this.customMessageSource = customMessageSource;
        this.messageCode = MessageCodeConstant.BASIC_INFORMATION;
    }

    //create
    @PostMapping("/save")
    public ResponseEntity<ResponseDto> createBasicInfo(@RequestBody BasicInformationDto basicInformationDto) {

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_CREATE, customMessageSource.get(messageCode)), basicInformationService
                .createBasicInformation(basicInformationDto)), HttpStatus.OK);


    }

    // update

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateBasicInfo(@RequestBody BasicInformationDto basicInformationDto, @PathVariable Short id) {

        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_UPDATE, customMessageSource
                        .get(MessageCodeConstant.BASIC_INFORMATION)), basicInformationService
                .updateBasicInformation(basicInformationDto, id)), HttpStatus.OK);
    }

    // delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteBasicInfo(@PathVariable Short id) {


        basicInformationService.deleteBasicInformation(id);

        return ResponseEntity.ok(
                successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE, customMessageSource.get(messageCode)), null));

    }

    // get

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getBasicInfoById(@PathVariable Short id) {

        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.
                get(MessageConstant.CRUD_GET,
                        customMessageSource.get(MessageCodeConstant.BASIC_INFORMATION)), basicInformationService.getBasicInformationById(id)), HttpStatus.OK);

    }

    // get all user

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllBasicInfo() {

       /* List<BasicInformationDto> allBasicInfo = basicInformationService.getAllBasicInfo();
        return ResponseEntity.ok(new ResponseDto("All Basic Information extracted!! : " +
                "सबै आधारभूत जानकारी निकालियो |", true, allBasicInfo));*/

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET_ALL, customMessageSource
                        .get(messageCode)), basicInformationService.getAllBasicInfo()), HttpStatus.OK);
    }

    // post profile image upload

    @PostMapping("/image/upload/{id}")
    public ResponseEntity<ResponseDto> uploadProfileImage(@RequestParam("image") MultipartFile image, @PathVariable Short id
    ) throws IOException {

        BasicInformationDto basicInformationDto = basicInformationService.getBasicInformationById(id);

        String fileName = fileService.uploadImage(path, image);

        basicInformationDto.setProfileImage(fileName);
        BasicInformationDto updateBasicInfo = basicInformationService.updateBasicInformation(basicInformationDto, id);
        return ResponseEntity.ok(new ResponseDto("Profile image uploaded", true, updateBasicInfo));
    }

    // method to serve file
    @GetMapping(value = "/image/{image-name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("image-name") String imageName, HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }
}
