package com.controllers;

public class Response<Payload> {
    private int status;
    private String message;
    private Payload data;

    public Response(int code, String msg, Payload data) {
        this.status = code;
        this.message = msg;
        this.data = data;
    }
}
