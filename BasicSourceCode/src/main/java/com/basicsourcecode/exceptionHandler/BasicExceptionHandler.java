package com.basicsourcecode.exceptionHandler;

import lombok.*;

@Builder
@Getter
public class BasicExceptionHandler extends RuntimeException {

    private String errorCode;
    private String detail;
    private String message;

    public BasicExceptionHandler(String message) {
        this.message = message;
    }

    public BasicExceptionHandler(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public BasicExceptionHandler(String errorCode, String message, String detail) {
        this.message = message;
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public BasicExceptionHandler(String errorCode, String message , String digit , String detail) {
        this.message = message.replace("{0}" , digit);
        this.errorCode = errorCode;
        this.detail = detail;
    }


    @Override
    public String toString() {
        return  "errorCode= " + this.errorCode + '\n' +
                "detail= " + this.detail + '\n' +
                "message= " + this.message ;
    }

}
