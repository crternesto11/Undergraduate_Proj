package com.fellowchicken.common;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ResultGenerator {
    public static @NotNull <T> Result<T> genSuccessResult() {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCodeEnum.RESULT_CODE_OK.getCode());
        result.setMessage(ResultCodeEnum.RESULT_CODE_OK.getMessage());
        return result;
    }

    public static @NotNull <T> Result<T> genSuccessResult(String message) {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCodeEnum.RESULT_CODE_OK.getCode());
        result.setMessage(message);
        return result;
    }

    public static @NotNull <T> Result<T> genSuccessResult(T data) {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCodeEnum.RESULT_CODE_OK.getCode());
        result.setMessage(ResultCodeEnum.RESULT_CODE_OK.getMessage());
        result.setData(data);
        return result;
    }

    public static @NotNull <T> Result<T> genFailResult(String message) {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCodeEnum.RESULT_CODE_INTERNAL_SERVER_ERROR.getCode());
        if (Objects.equals(message, "")) {
            result.setMessage(ResultCodeEnum.RESULT_CODE_INTERNAL_SERVER_ERROR.getMessage());
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static @NotNull <T> Result<T> genFailResult(T data) {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCodeEnum.RESULT_CODE_FAILED.getCode());
        result.setMessage(ResultCodeEnum.RESULT_CODE_FAILED.getMessage());
        result.setData(data);
        return result;
    }

    public static @NotNull <T> Result<T> genNullResult(String message) {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCodeEnum.RESULT_CODE_BAD_REQUEST.getCode());
        result.setMessage(message);
        return result;
    }

    public static @NotNull <T> Result<T> genResult(int code, String message) {
        Result<T> result = new Result<>();
        result.setResultCode(code);
        result.setMessage(message);
        return result;
    }

}
