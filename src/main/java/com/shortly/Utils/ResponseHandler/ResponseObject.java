package com.shortly.Utils.ResponseHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseObject {
    private int status;
    private String message;
    private Object data;
    private String errorCode;
}