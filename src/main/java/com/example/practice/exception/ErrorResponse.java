package com.example.practice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String code;
    private String message;
    private String path;
    private Map<String, String> errors;

//    public ErrorResponse(LocalDateTime timestamp, int status, String code,
//                         String message, String path, Map<String, String> errors) {
//        this.timestamp = timestamp;
//        this.status = status;
//        this.code = code;
//        this.message = message;
//        this.path = path;
//        this.errors = errors;
//    }
}