package com.gymbuddy.service.impl.utils;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.ContactDetailsDTO;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.model.dto.GymLocationDTO;
import com.gymbuddy.model.dto.WorkingInfoDTO;
import com.gymbuddy.model.enumerations.ContactType;
import com.gymbuddy.model.shared.ContactDetails;
import com.gymbuddy.model.shared.GymLocation;
import com.gymbuddy.model.shared.WorkingInfo;
import com.gymbuddy.repository.ContactDetailsRepository;
import com.gymbuddy.repository.GymLocationRepository;
import com.gymbuddy.repository.WorkingInfoRepository;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GymDTOToClassMapper
{
    public static Gym mapProperties(GymDTO gymDTO, Logger logger, ContactDetailsRepository contactDetailsRepository, GymLocationRepository gymLocationRepository, WorkingInfoRepository workingInfoRepository)
    {
        Gym toSave = new Gym();
        toSave.setGymName(gymDTO.getGymName());
        toSave.setLocationId(saveAndRetrieveGymLocationId(gymDTO.getLocationDTO(), gymLocationRepository, logger));
        toSave.setContactDetailsIds(saveAndRetrieveContactDetailsIds(gymDTO.getContactDetailsDTO(), contactDetailsRepository, logger));
        toSave.setGymDescription(gymDTO.getGymDescription());
        toSave.setWorkingInformationId(saveAndRetrieveWorkingInfoId(gymDTO.getWorkingInfoDTO(), workingInfoRepository, logger));
        toSave.setGymMembership(gymDTO.getGymMembership());
        toSave.setGym24(gymDTO.isGym24());
        toSave.setGymCapacity(gymDTO.getGymCapacity());
        toSave.setImagePath(gymDTO.getImagePath());

        return toSave;
    }

    private static Long saveAndRetrieveWorkingInfoId(WorkingInfoDTO workingInfoDTO, WorkingInfoRepository workingInfoRepository, Logger logger)
    {
        WorkingInfo workingInfo = mapToWorkingInfo(workingInfoDTO);

        try
        {
            workingInfo = workingInfoRepository.save(workingInfo);
        }
        catch (RuntimeException e)
        {
            logger.error("Error saving WorkingInfo", e);
        }

        return workingInfo.getId();
    }

    private static WorkingInfo mapToWorkingInfo(WorkingInfoDTO workingInfoDTO)
    {
        WorkingInfo workingInfo = new WorkingInfo();

        workingInfo.setClosingTime(workingInfoDTO.getClosingTime());
        workingInfo.setOpeningTime(workingInfoDTO.getOpeningTime());
        workingInfo.setDay(workingInfoDTO.getDay());

        return workingInfo;
    }

    private static List<Long> saveAndRetrieveContactDetailsIds(List<ContactDetailsDTO> contactDetailsDTO, ContactDetailsRepository contactDetailsRepository, Logger logger)
    {
        List<ContactDetails> contactDetailsList = contactDetailsDTO.stream()
                .map(GymDTOToClassMapper::mapToContactDetails)
                .toList();

        contactDetailsList.forEach(contactDetails -> {
            try
            {
                contactDetailsRepository.save(contactDetails);
            }
            catch (RuntimeException e)
            {
                logger.error("Error saving ContactDetails", e);
            }
        });

        return contactDetailsList.stream()
                .map(ContactDetails::getId)
                .collect(Collectors.toList());
    }

    private static ContactDetails mapToContactDetails(ContactDetailsDTO contactDetailsDTO)
    {
        ContactDetails contactDetails = new ContactDetails();

        contactDetails.setContact(contactDetailsDTO.getContact());
        contactDetails.setContactType(
                Arrays.stream(ContactType.values())
                        .filter(ct -> ct.name().equalsIgnoreCase(contactDetailsDTO.getContactType()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid ContactType: " + contactDetailsDTO.getContactType()))
        );


        return contactDetails;
    }

    private static Long saveAndRetrieveGymLocationId(GymLocationDTO locationDTO, GymLocationRepository gymLocationRepository, Logger logger)
    {
        GymLocation location = mapToGymLocation(locationDTO);

        try
        {
            location = gymLocationRepository.save(location);
        }
        catch (RuntimeException e)
        {
            logger.error("Error saving GymLocation", e);
        }

        return location.getId();
    }

    private static GymLocation mapToGymLocation(GymLocationDTO locationDTO)
    {
        GymLocation location = new GymLocation();

        location.setGymState(locationDTO.getGymState());
        location.setGymAddress(locationDTO.getGymAddress());
        location.setZipCode(locationDTO.getZipCode());
        location.setGymCity(locationDTO.getGymCity());

        return location;
    }
}
