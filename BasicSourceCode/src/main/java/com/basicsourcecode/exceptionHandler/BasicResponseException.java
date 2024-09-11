package com.basicsourcecode.exceptionHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BasicResponseException {

    private static BasicResponseException instance;
    private String message;
    private String errorCode;
    private String detailResponse;

    private BasicResponseException() {
    }

    private BasicResponseException(String message, String errorCode, String detailResponse) {
        this.message = message;
        this.errorCode = errorCode;
        this.detailResponse = detailResponse;
    }

    private BasicResponseException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public static BasicResponseException createError() {
        return instance = new BasicResponseException();
    }

    public static BasicResponseException createError(String message, String errorCode, String detail) {
        return instance = new BasicResponseException(message, errorCode, detail);
    }

    public static BasicResponseException createError(String message, String errorCode) {
        return instance = new BasicResponseException(message, errorCode);
    }


}
