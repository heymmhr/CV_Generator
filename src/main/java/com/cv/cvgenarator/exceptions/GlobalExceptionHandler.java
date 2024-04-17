package com.cv.cvgenarator.exceptions;

import com.cv.cvgenarator.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        String message = ex.getMessage();
        ResponseDto apiResponse = new ResponseDto(message, false, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){

        Map <String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseDto> handleHttpMessageConversionException(HttpMessageConversionException ex) {
        String message = ex.getMessage();
        ResponseDto apiResponse = new ResponseDto(message, false, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }

}
