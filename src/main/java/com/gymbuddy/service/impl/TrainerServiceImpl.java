package com.gymbuddy.service.impl;

import com.gymbuddy.model.Trainer;
import com.gymbuddy.model.dto.ContactDetailsDTO;
import com.gymbuddy.model.dto.TrainerCertificationDTO;
import com.gymbuddy.model.dto.TrainerDTO;
import com.gymbuddy.model.enumerations.ContactType;
import com.gymbuddy.model.enumerations.WorkingShift;
import com.gymbuddy.model.shared.ContactDetails;
import com.gymbuddy.model.shared.TrainerCertification;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        Trainer toSave = new Trainer();
        mapProperties(toSave, trainerDTO);
        return getTrainerRepository().save(toSave);
    }

    private void mapProperties(Trainer toSave, TrainerDTO trainerDTO)
    {
        toSave.setFirstName(trainerDTO.getFirstName());
        toSave.setLastName(trainerDTO.getLastName());
        toSave.setContactDetailsIds(saveAndRetrieveIds(
                trainerDTO.getContactDetails(),
                this::mapToContactDetails,
                contactDetailsRepository::save));
        toSave.setExperienceYears(trainerDTO.getExperienceYears());
        toSave.setCertificationsIds(saveAndRetrieveIds(
                trainerDTO.getCertifications(),
                this::mapToTrainerCertification,
                getTrainerCertificationRepository()::save));
        toSave.setHourlyRate(trainerDTO.getHourlyRate());
        toSave.setShift(WorkingShift.valueOf(trainerDTO.getShift()));
        toSave.setBiography(trainerDTO.getBiography());
    }

    private <T, D> List<Long> saveAndRetrieveIds(List<D> dtoList, Function<D, T> mapper, Consumer<T> saver)
    {
        List<T> entityList = dtoList.stream()
                .map(mapper)
                .collect(Collectors.toList());

        entityList.forEach(entity -> {
            try
            {
                saver.accept(entity);
            }
            catch (RuntimeException e)
            {
                logger.error("Error saving entity: " + entity.getClass().getSimpleName(), e);
            }
        });

        return entityList.stream()
                .map(this::extractId)
                .collect(Collectors.toList());
    }

    private <T> Long extractId(T entity)
    {
        if (entity instanceof TrainerCertification)
        {
            return ((TrainerCertification) entity).getId();
        }
        else if (entity instanceof ContactDetails)
        {
            return ((ContactDetails) entity).getId();
        }

        throw new IllegalArgumentException("Unsupported entity type: " + entity.getClass());
    }

    private TrainerCertification mapToTrainerCertification(TrainerCertificationDTO trainerCertificationDTO)
    {
        TrainerCertification trainerCertification = new TrainerCertification();

        trainerCertification.setDateIssued(trainerCertificationDTO.getDateIssued());
        trainerCertification.setValidUntil(trainerCertificationDTO.getValidUntil());
        trainerCertification.setIssuedFrom(trainerCertificationDTO.getIssuedFrom());
        trainerCertification.setCertificationName(trainerCertificationDTO.getCertificationName());
        trainerCertification.setValid(checkIsValid(trainerCertificationDTO.getValidUntil()));

        return trainerCertification;
    }

    private ContactDetails mapToContactDetails(ContactDetailsDTO contactDetailsDTO)
    {
        ContactDetails contactDetails = new ContactDetails();

        contactDetails.setContact(contactDetailsDTO.getContact());
        contactDetails.setContactType(ContactType.valueOf(contactDetailsDTO.getContactType()));

        return contactDetails;
    }

    public static boolean checkIsValid(Date validUntil)
    {
        if (validUntil == null)
        {
            throw new IllegalArgumentException("The validUntil date cannot be null");
        }

        LocalDate validUntilLocalDate = validUntil.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return validUntilLocalDate.isBefore(LocalDate.now());
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
