package com.basicsourcecode.exceptionHandler;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.stream.Collectors;

@ControllerAdvice
public class BasicGlobalExceptionHandler {

    @ExceptionHandler(BasicExceptionHandler.class)
    public ResponseEntity<?> HandlerException(BasicExceptionHandler ex){
        return new ResponseEntity<>(BasicResponseException.createError(ex.getMessage(), ex.getErrorCode() , ex.getDetail()), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("handleValidationExceptions ====> " + ex);
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        String exception = BasicExceptionHandler.builder().message(errorMessage)
                .errorCode(BasicConstantErrorCodeAndMessage.ENTERED_VALUE_IS_NOT_VALID_CODE)
                .detail(errorMessage).build().toString();
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        String errorMessage = ex.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        String exception = BasicExceptionHandler.builder().message(errorMessage)
                .errorCode(BasicConstantErrorCodeAndMessage.ENTERED_VALUE_IS_NOT_VALID_CODE)
                .detail(BasicConstantErrorCodeAndMessage.ENTERED_VALUE_IS_NOT_VALID_MESSAGE).build().toString();
        return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST );
    }

}
