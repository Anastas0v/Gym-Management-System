package com.gymbuddy.model;

import com.gymbuddy.model.shared.ContactDetails;
import com.gymbuddy.model.shared.GymLocation;
import com.gymbuddy.model.shared.WorkingInfo;
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

    //temporary solution. after final definition of entity properties, will remove the database relations
    //for better performance
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "gym_location_id")
    private GymLocation location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gym_id")
    private List<ContactDetails> contactDetails;

    @Column(name = "gym_description")
    private String gymDescription;

    @OneToOne(cascade = CascadeType.ALL)
    private WorkingInfo workingInformation;

    @Column(name = "gym_membership")
    private Integer gymMembership;

    @Column(name = "is_gym_24")
    private boolean isGym24;

    @Column(name = "gym_capacity")
    private Integer gymCapacity;
}
