package com.mcenk.percentileapi.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler
   Map<String,String> exceptionHandler(UserNotFoundException userNotFoundException){
        Map<String,String> errorMessage= new HashMap<>();
        errorMessage.put("Error Message", userNotFoundException.getMessage());
        return errorMessage;
    }
}
