package com.gymbuddy.service.impl;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.ContactDetailsDTO;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.model.dto.GymLocationDTO;
import com.gymbuddy.model.dto.WorkingInfoDTO;
import com.gymbuddy.model.enumerations.ContactType;
import com.gymbuddy.model.shared.ContactDetails;
import com.gymbuddy.model.shared.GymLocation;
import com.gymbuddy.model.shared.WorkingInfo;
import com.gymbuddy.repository.GymRepository;
import com.gymbuddy.service.GymService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class GymServiceImpl implements GymService
{
    @Autowired
    private GymRepository gymRepository;

    @Override
    public List<Gym> findAll()
    {
        return getGymRepository().findAll();
    }

    @Override
    public Gym findById(Long id)
    {
        return getGymRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Gym Not Found With ID: " + id));
    }

    @Override
    public Gym create(GymDTO gymDTO)
    {
        if (gymDTO == null)
        {
            throw new IllegalArgumentException("Required Object Is Null...Error!");
        }

        Gym toSave = new Gym();
        mapProperties(toSave, gymDTO);
        return getGymRepository().save(toSave);
    }

    private void mapProperties(Gym toSave, GymDTO gymDTO)
    {
        toSave.setGymName(gymDTO.getGymName());
        toSave.setLocation(buildGymLocation(gymDTO.getLocationDTO()));
        toSave.setContactDetails(buildGymContactDetails(gymDTO.getContactDetailsDTO()));
        toSave.setGymDescription(gymDTO.getGymDescription());
        toSave.setWorkingInformation(buildGymWorkingInfo(gymDTO.getWorkingInfoDTO()));
        toSave.setGymMembership(gymDTO.getGymMembership());
        toSave.setGym24(gymDTO.isGym24());
        toSave.setGymCapacity(gymDTO.getGymCapacity());
    }

    private WorkingInfo buildGymWorkingInfo(WorkingInfoDTO workingInfoDTO)
    {
        WorkingInfo workingInfo = new WorkingInfo();

        workingInfo.setClosingTime(workingInfoDTO.getClosingTime());
        workingInfo.setOpeningTime(workingInfoDTO.getOpeningTime());
        workingInfo.setDay(workingInfo.getDay());

        return workingInfo;
    }

    private List<ContactDetails> buildGymContactDetails(List<ContactDetailsDTO> contactDetailsDTO)
    {
        List<ContactDetails> contactDetailsList = new ArrayList<>();

        for (ContactDetailsDTO contactDetails : contactDetailsDTO)
        {
            ContactDetails newContactDetails = new ContactDetails();
            newContactDetails.setContact(contactDetails.getContact());
            newContactDetails.setContactType(ContactType.valueOf(contactDetails.getContactType()));

            contactDetailsList.add(newContactDetails);
        }

        return contactDetailsList;
    }

    private GymLocation buildGymLocation(GymLocationDTO locationDTO)
    {
        GymLocation location = new GymLocation();

        location.setGymState(locationDTO.getGymState());
        location.setGymAddress(locationDTO.getGymAddress());
        location.setZipCode(locationDTO.getZipCode());
        location.setGymCity(locationDTO.getGymCity());

        return location;
    }

    @Override
    public Gym update(Gym gym)
    {
        if (gym == null || gym.getId() == null)
        {
            throw new IllegalArgumentException("Required Data Missing...Error!");
        }

        Gym existingGym = findById(gym.getId());

        if(existingGym != null)
        {
            BeanUtils.copyProperties(gym, existingGym);
        }
        else
        {
            throw new IllegalArgumentException("Required Object Is Null...Error!");
        }

        return getGymRepository().save(existingGym);
    }


    @Override
    public void delete(Long id)
    {
        if(findById(id) == null)
        {
            throw new IllegalArgumentException();
        }
        getGymRepository().deleteById(id);
    }
}