package com.gymbuddy.model;

import com.gymbuddy.model.enumerations.WorkingShift;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "trainer")
public class Trainer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long id;

    @Column(name = "trainer_firstName", nullable = false)
    private String firstName;

    @Column(name = "trainer_lastName", nullable = false)
    private String lastName;

    @ElementCollection
    @Column(name = "contact_details_ids")
    private List<Long> contactDetailsIds;

    @Column(name = "trainer_experienceYears", nullable = false)
    private Integer experienceYears;

    @ElementCollection
    @Column(name = "certifications_ids")
    private List<Long> certificationsIds;

    @Column(name = "trainer_hourlyRate", nullable = false)
    private Integer hourlyRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "trainer_shift", nullable = false)
    private WorkingShift shift;

    @Column(name = "trainer_biography")
    private String biography;

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
