package com.gymbuddy.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainerDTO
{
    private String firstName;

    private String lastName;

    private List<ContactDetailsDTO> contactDetails;

    private Integer experienceYears;

    private List<TrainerCertificationDTO> certifications;

    private Integer hourlyRate;

    private String shift;

    private String biography;

    private String imagePath;
}
