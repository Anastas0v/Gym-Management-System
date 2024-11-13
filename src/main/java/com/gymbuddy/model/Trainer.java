package com.gymbuddy.model;

import com.gymbuddy.model.enumerations.WorkingShift;
import com.gymbuddy.model.shared.ContactDetails;
import com.gymbuddy.model.shared.TrainerCertification;
import jakarta.persistence.*;
import lombok.Data;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "trainer_id")
    private List<ContactDetails> contactDetails;

    @Column(name = "trainer_experienceYears", nullable = false)
    private Integer experienceYears;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "trainer_id")
    private List<TrainerCertification> certifications;

    @Column(name = "trainer_hourlyRate", nullable = false)
    private Integer hourlyRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "trainer_shift", nullable = false)
    private WorkingShift shift;

    @Column(name = "trainer_biography")
    private String biography;
}
