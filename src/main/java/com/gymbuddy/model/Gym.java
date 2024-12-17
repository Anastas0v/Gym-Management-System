package com.gymbuddy.model;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "gym")
public class Gym
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_id", nullable = false)
    private Long id;

    @Column(name = "gym_name", nullable = false)
    private String gymName;

    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @ElementCollection
    @Column(name = "contact_details_ids", nullable = false)
    private List<Long> contactDetailsIds;

    @Column(name = "gym_description")
    private String gymDescription;

    @Column(name = "working_information_id", nullable = false)
    private Long workingInformationId;

    @Column(name = "gym_membership", nullable = false)
    private Integer gymMembership;

    @Column(name = "is_gym_24")
    private boolean isGym24;

    @Column(name = "gym_capacity")
    private Integer gymCapacity;

    private String imagePath;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @PrePersist
    protected void onCreate()
    {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        modified = new Date();
    }
}
