package com.hashwallet.foundation;

import lombok.Data;

@Data
public class Response {
    private int code;
    private String message;
    private Object data;

    public Response(Object data) {
        this.code = 200;
        this.message = "SUCCESS";
        this.data = data;
    }
}
