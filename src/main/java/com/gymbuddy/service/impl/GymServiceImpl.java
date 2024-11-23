package com.gymbuddy.service.impl;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.repository.ContactDetailsRepository;
import com.gymbuddy.repository.GymLocationRepository;
import com.gymbuddy.repository.GymRepository;
import com.gymbuddy.repository.WorkingInfoRepository;
import com.gymbuddy.service.GymService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gymbuddy.service.impl.utils.GymDTOToClassMapper.mapProperties;

@Service
@Getter
public class GymServiceImpl implements GymService
{
    private static final Logger logger = LoggerFactory.getLogger(GymServiceImpl.class);

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private GymLocationRepository gymLocationRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private WorkingInfoRepository workingInfoRepository;

    @Override
    public List<Gym> findAll()
    {
        return getGymRepository().findAll();
    }

    @Override
    public Gym findById(Long id)
    {
        return getGymRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gym not found with ID: " + id));
    }

    @Override
    public Gym create(GymDTO gymDTO)
    {
        if (gymDTO == null)
        {
            throw new IllegalArgumentException("The provided object is null. Please check the input data.");
        }

        return getGymRepository().save(mapProperties(gymDTO, logger, getContactDetailsRepository(), getGymLocationRepository(), getWorkingInfoRepository()));
    }

    @Override
    public Gym update(Gym gym)
    {
        if (gym == null || gym.getId() == null)
        {
            throw new IllegalArgumentException("The provided gym object or ID is missing.");
        }

        if (!getGymRepository().existsById(gym.getId()))
        {
            throw new IllegalArgumentException("The gym with the given ID was not found.");
        }

        return getGymRepository().save(gym);
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