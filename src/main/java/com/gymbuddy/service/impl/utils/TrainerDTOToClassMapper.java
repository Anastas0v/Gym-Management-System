package com.gymbuddy.service.impl.utils;

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
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TrainerDTOToClassMapper
{
    public static Trainer mapProperties(TrainerDTO trainerDTO, ContactDetailsRepository contactDetailsRepository, TrainerCertificationRepository trainerCertificationRepository, Logger logger)
    {
        Trainer toSave = new Trainer();

        toSave.setFirstName(trainerDTO.getFirstName());
        toSave.setLastName(trainerDTO.getLastName());
        toSave.setContactDetailsIds(saveAndRetrieveIds(
                logger,
                trainerDTO.getContactDetails(),
                TrainerDTOToClassMapper::mapToContactDetails,
                contactDetailsRepository::save));
        toSave.setExperienceYears(trainerDTO.getExperienceYears());
        toSave.setCertificationsIds(saveAndRetrieveIds(
                logger,
                trainerDTO.getCertifications(),
                TrainerDTOToClassMapper::mapToTrainerCertification,
                trainerCertificationRepository::save));
        toSave.setHourlyRate(trainerDTO.getHourlyRate());
        toSave.setShift(WorkingShift.valueOf(trainerDTO.getShift()));
        toSave.setBiography(trainerDTO.getBiography());

        return toSave;
    }

    private static <T, D> List<Long> saveAndRetrieveIds(Logger logger, List<D> dtoList, Function<D, T> mapper, Consumer<T> saver)
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
                .map(TrainerDTOToClassMapper::extractId)
                .collect(Collectors.toList());
    }

    private static <T> Long extractId(T entity)
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

    private static TrainerCertification mapToTrainerCertification(TrainerCertificationDTO trainerCertificationDTO)
    {
        TrainerCertification trainerCertification = new TrainerCertification();

        trainerCertification.setDateIssued(trainerCertificationDTO.getDateIssued());
        trainerCertification.setValidUntil(trainerCertificationDTO.getValidUntil());
        trainerCertification.setIssuedFrom(trainerCertificationDTO.getIssuedFrom());
        trainerCertification.setCertificationName(trainerCertificationDTO.getCertificationName());
        trainerCertification.setValid(checkIsValid(trainerCertificationDTO.getValidUntil()));

        return trainerCertification;
    }

    private static ContactDetails mapToContactDetails(ContactDetailsDTO contactDetailsDTO)
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
}
