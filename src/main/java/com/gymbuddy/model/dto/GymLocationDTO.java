package com.gymbuddy.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymLocationDTO
{
    private String gymAddress;

    private String gymCity;

    private String gymState;

    private Integer zipCode;
}
