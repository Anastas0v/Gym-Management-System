package com.gymbuddy.model;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;

import java.util.List;

@Data
@Entity(name = "gym")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_id", nullable = false)
    private Long id;

    @Column(name = "gym_name")
    private String gymName;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "contact_details_ids")
    private List<Long> contactDetailsIds;

    @Column(name = "gym_description")
    private String gymDescription;

    @Column(name = "working_information_id")
    private Long workingInformationId;

    @Column(name = "gym_membership")
    private Integer gymMembership;

    @Column(name = "is_gym_24")
    private boolean isGym24;

    @Column(name = "gym_capacity")
    private Integer gymCapacity;
}
