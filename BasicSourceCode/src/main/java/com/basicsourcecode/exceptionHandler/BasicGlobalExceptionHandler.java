package com.basicsourcecode.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BasicGlobalExceptionHandler {

    @ExceptionHandler(BasicExceptionHandler.class)
    public ResponseEntity<?> HandlerException(BasicExceptionHandler ex){
        return new ResponseEntity<>(BasicResponseException.createError(ex.getMessage(), ex.getErrorCode() , ex.getDetail()), HttpStatus.OK);
    }


}
