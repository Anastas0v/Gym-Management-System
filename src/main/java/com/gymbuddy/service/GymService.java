package com.gymbuddy.service;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.GymDTO;

import java.util.List;

public interface GymService
{
    List<Gym> findAll();

    Gym findById(Long id);

    Gym update(Gym gym);

    Gym create(GymDTO gymDTO);

    void delete(Long id);
}
