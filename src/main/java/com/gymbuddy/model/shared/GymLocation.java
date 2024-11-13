package com.gymbuddy.model.shared;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "gym_location")
public class GymLocation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_location_id")
    private Long id;

    @Column(name = "gym_address")
    private String gymAddress;

    @Column(name = "gym_city")
    private String gymCity;

    @Column(name = "gym_state")
    private String gymState;

    @Column(name = "zip_code")
    private Integer zipCode;
}
