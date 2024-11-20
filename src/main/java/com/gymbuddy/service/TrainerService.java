package com.gymbuddy.service;

import com.gymbuddy.model.Trainer;
import com.gymbuddy.model.dto.TrainerDTO;

import java.util.List;

public interface TrainerService
{
    List<Trainer> findAll();

    Trainer findById(Long id);

    Trainer create(TrainerDTO trainerDTO);

    Trainer update(Trainer trainer);

    void delete(Long id);
}
