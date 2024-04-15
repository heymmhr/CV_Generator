package com.cv.cvgenarator.controller;

import com.cv.cvgenarator.config.CustomMessageSource;
import com.cv.cvgenarator.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {


    public String messageCode;

    @Autowired
    public CustomMessageSource customMessageSource;

    @Autowired
    public ObjectMapper objectMapper;

    public ResponseDto successResponse(String message, Object data){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(true);
        responseDto.setMessage(message);
        responseDto.setData(data);
        return responseDto;
    }

    public ResponseDto errorResponse(String message, Object errors){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(false);
        responseDto.setMessage(message);
        responseDto.setData(errors);
        return responseDto;
    }



}
