package com.fellowchicken.common;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    RESULT_CODE_CONTINUE(100, "Continue"),
    RESULT_CODE_OK(200, "OK"),
    RESULT_CODE_FAILED(-200, "Failed"),
    RESULT_CODE_CREATED(201, "Created"),
    RESULT_CODE_ACCEPTED(202, "Accepted"),
    RESULT_CODE_BAD_REQUEST(400, "Bad Request"),
    RESULT_CODE_FORBIDDEN(403, "Forbidden"),
    RESULT_CODE_NOT_FOUND(404, "Not Found"),
    RESULT_CODE_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    RESULT_CODE_NOT_IMPLEMENTED(501, "Not Implemented"),
    RESULT_CODE_BAD_GATEWAY(502, "Bad Gateway");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
