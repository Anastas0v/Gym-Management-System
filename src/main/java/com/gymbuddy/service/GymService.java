package com.gymbuddy.service;

import com.gymbuddy.model.Gym;

import java.util.List;

public interface GymService
{
    List<Gym> findAll();

    Gym findById(Long id);

    Gym update(Long id, Gym gym);

    Gym create(Long id, String name, String address, String city, String state, Integer zipCode, Integer phone, String email, String website, String description, String workingHours, Integer membership, boolean isGym24, Integer capacity);

    void delete(Long id);
}
