package com.gymbuddy.service.impl;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.repository.GymRepository;
import com.gymbuddy.service.GymService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        toSave.setGymAddress(gymDTO.getGymAddress());
        toSave.setGymCity(gymDTO.getGymCity());
        toSave.setGymState(gymDTO.getGymState());
        toSave.setZipCode(gymDTO.getZipCode());
        toSave.setGymDescription(gymDTO.getGymDescription());
        toSave.setGymMembership(gymDTO.getGymMembership());
        toSave.setGym24(gymDTO.isGym24());
        toSave.setGymCapacity(gymDTO.getGymCapacity());
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