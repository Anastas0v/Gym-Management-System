package com.gymbuddy.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GymDTO
{
    private String gymName;

    private GymLocationDTO locationDTO;

    private List<ContactDetailsDTO> contactDetailsDTO;

    private String gymDescription;

    private WorkingInfoDTO workingInfoDTO;

    private Integer gymMembership;

    private boolean isGym24;

    private Integer gymCapacity;

    private String imagePath;
}
