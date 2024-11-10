package com.gymbuddy.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TrainerCertificationDTO
{
    private Date dateIssued;

    private Date validUntil;

    private String issuedFrom;

    private String certificationName;

    private boolean isValid;
}
