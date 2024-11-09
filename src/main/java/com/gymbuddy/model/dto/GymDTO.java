package com.gymbuddy.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymDTO
{
    private Long id;

    private String gymName;

    private String gymAddress;

    private String gymCity;

    private String gymState;

    private Integer zipCode;

    private Integer phoneNumber;

    private String gymEmail;

    private String gymWebsite;

    private String gymDescription;

    private String gymWorkingHours;

    private Integer gymMembership;

    private boolean isGym24;

    private Integer gymCapacity;
}
