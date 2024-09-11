package com.basicsourcecode.exceptionHandler;

public class BasicExceptionHandler extends RuntimeException {

    private String errorCode;
    private String detail;

    public BasicExceptionHandler(String message) {
        super(message);
    }

    public BasicExceptionHandler(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BasicExceptionHandler(String errorCode, String message, String detail) {
        super(message);
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public BasicExceptionHandler(String errorCode, String message , String digit , String detail) {
        super(message.replace("{0}" , digit));
        this.errorCode = errorCode;
        this.detail = detail;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getDetail() {
        return detail;
    }

}
