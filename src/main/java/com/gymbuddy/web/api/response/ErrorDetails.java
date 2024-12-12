package com.gymbuddy.web.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails
{
    private String error;
    private String message;
}
