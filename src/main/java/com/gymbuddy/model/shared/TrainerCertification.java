package com.gymbuddy.model.shared;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "trainer_certification")
public class TrainerCertification
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_certification_id")
    private Long id;

    @Column(name = "certification_dateIssued", nullable = false)
    private Date dateIssued;

    @Column(name = "certification_validUntil", nullable = false)
    private Date validUntil;

    @Column(name = "certification_issuedFrom", nullable = false)
    private String issuedFrom;

    @Column(name = "certification_name", nullable = false)
    private String certificationName;

    //if certification is below validUntil date it is valid
    @Column(name = "certification_isValid", nullable = false)
    private boolean isValid;
}
