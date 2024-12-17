package com.gymbuddy.service.integration.utils;

import com.gymbuddy.model.dto.ContactDetailsDTO;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.model.dto.GymLocationDTO;
import com.gymbuddy.model.dto.WorkingInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class GymDTOToClassMapper
{
    public static GymDTO mapGym()
    {
        GymDTO dto = new GymDTO();

        dto.setGymName("TestGym");
        dto.setLocationDTO(createLocationDTO());
        dto.setContactDetailsDTO(createContactList());
        dto.setGymDescription("GymDescription");
        dto.setWorkingInfoDTO(createWorkingInfo());
        dto.setGymMembership(1000);
        dto.setGym24(true);
        dto.setGymCapacity(10);

        return dto;
    }

    public static WorkingInfoDTO createWorkingInfo()
    {
        WorkingInfoDTO workingInfoDTO = new WorkingInfoDTO();

        workingInfoDTO.setDay("Monday");
        workingInfoDTO.setOpeningTime("07:00");
        workingInfoDTO.setClosingTime("24:00");

        return workingInfoDTO;
    }

    public static List<ContactDetailsDTO> createContactList()
    {
        List<ContactDetailsDTO> contactDetailsDTOList = new ArrayList<>();

        ContactDetailsDTO contactDetails = new ContactDetailsDTO();
        contactDetails.setContactType("Email");
        contactDetails.setContact("example@example.com");

        contactDetailsDTOList.add(contactDetails);

        return contactDetailsDTOList;
    }

    public static GymLocationDTO createLocationDTO()
    {
        GymLocationDTO gymLocationDTO = new GymLocationDTO();

        gymLocationDTO.setGymAddress("Address");
        gymLocationDTO.setGymCity("Skopje");
        gymLocationDTO.setGymState("MKD");
        gymLocationDTO.setZipCode(1000);

        return gymLocationDTO;
    }
}
