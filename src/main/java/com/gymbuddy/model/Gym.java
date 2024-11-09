package com.gymbuddy.model;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity(name = "gym")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "gym_id")
    private Long id;

    @Column(name = "gym_name")
    private String gymName;

    @Column(name = "gym_address")
    private String gymAddress;

    @Column(name = "gym_city")
    private String gymCity;

    @Column(name = "gym_state")
    private String gymState;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "gym_email")
    private String gymEmail;

    @Column(name = "gym_website")
    private String gymWebsite;

    @Column(name = "gym_description")
    private String gymDescription;

    @Column(name = "gym_working_hours")
    private String gymWorkingHours;

    @Column(name = "gym_membership")
    private Integer gymMembership;

    @Column(name = "is_gym_24")
    private boolean isGym24;

    @Column(name = "gym_capacity")
    private Integer gymCapacity;

    public Gym(Long id, String gymName, String gymAddress, String gymCity, String gymState, Integer zipCode, Integer phoneNumber, String gymEmail, String gymWebsite, String gymDescription, String gymWorkingHours, Integer gymMembership, boolean isGym24, Integer gymCapacity) {
        this.id = id;
        this.gymName = gymName;
        this.gymAddress = gymAddress;
        this.gymCity = gymCity;
        this.gymState = gymState;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.gymEmail = gymEmail;
        this.gymWebsite = gymWebsite;
        this.gymDescription = gymDescription;
        this.gymWorkingHours = gymWorkingHours;
        this.gymMembership = gymMembership;
        this.isGym24 = isGym24;
        this.gymCapacity = gymCapacity;
    }
}
