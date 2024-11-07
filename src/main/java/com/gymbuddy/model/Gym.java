package com.gymbuddy.model;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
public class Gym
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

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

    private boolean gymAccess;

    private Integer gymCapacity;
}
