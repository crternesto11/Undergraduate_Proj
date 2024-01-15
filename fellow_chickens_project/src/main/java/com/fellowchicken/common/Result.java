package com.fellowchicken.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer resultCode;
    private String message;
    private T data;

    public Result() {
    }
}
