package com.gymbuddy.web.api.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse<T>
{
    private boolean success;
    private int statusCode;
    private T data;
    private ErrorDetails errorDetails;
    private String timestamp;

    public ApiResponse(boolean success, int statusCode, T data, ErrorDetails errorDetails)
    {
        this.success = success;
        this.statusCode = statusCode;
        this.data = data;
        this.errorDetails = errorDetails;
        this.timestamp = LocalDateTime.now().toString();
    }
}
