package com.gymbuddy.service.impl;

import com.gymbuddy.model.Trainer;
import com.gymbuddy.model.dto.TrainerDTO;
import com.gymbuddy.repository.ContactDetailsRepository;
import com.gymbuddy.repository.TrainerCertificationRepository;
import com.gymbuddy.repository.TrainerRepository;
import com.gymbuddy.service.TrainerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gymbuddy.service.impl.utils.TrainerDTOToClassMapper.mapProperties;

@Service
@Getter
public class TrainerServiceImpl implements TrainerService
{
    private static final Logger logger = LoggerFactory.getLogger(GymServiceImpl.class);

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private TrainerCertificationRepository trainerCertificationRepository;

    @Override
    public List<Trainer> findAll()
    {
        return getTrainerRepository().findAll();
    }

    @Override
    public Trainer findById(Long id)
    {
        return getTrainerRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found with ID: " + id));
    }

    @Override
    public Trainer create(TrainerDTO trainerDTO)
    {
        if (trainerDTO == null)
        {
            throw new IllegalArgumentException("The provided object is null. Please check the input data.");
        }

        Trainer toSave = mapProperties(trainerDTO, getContactDetailsRepository(), getTrainerCertificationRepository(), logger);
        return getTrainerRepository().save(toSave);
    }

    @Override
    public Trainer update(Trainer trainer)
    {
        if (trainer == null || trainer.getId() == null)
        {
            throw new IllegalArgumentException("The provided trainer object or ID is missing.");
        }

        Trainer existingTrainer = findById(trainer.getId());

        if (existingTrainer != null)
        {
            BeanUtils.copyProperties(trainer, existingTrainer);
        }
        else
        {
            throw new IllegalArgumentException("Trainer with given ID was not found.");
        }

        return getTrainerRepository().save(existingTrainer);
    }

    @Override
    public void delete(Long id)
    {
        if(findById(id) == null)
        {
            throw new IllegalArgumentException();
        }
        getTrainerRepository().deleteById(id);
    }
}
