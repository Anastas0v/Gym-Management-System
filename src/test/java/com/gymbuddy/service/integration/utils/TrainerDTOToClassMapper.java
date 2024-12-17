package com.gymbuddy.service.integration.utils;

import com.gymbuddy.model.dto.ContactDetailsDTO;
import com.gymbuddy.model.dto.TrainerCertificationDTO;
import com.gymbuddy.model.dto.TrainerDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TrainerDTOToClassMapper
{
    public static TrainerDTO mapTrainer()
    {
        TrainerDTO trainerDTO = new TrainerDTO();

        trainerDTO.setFirstName("John");
        trainerDTO.setLastName("Doe");
        trainerDTO.setContactDetails(createContactDetails());
        trainerDTO.setExperienceYears(5);
        trainerDTO.setCertifications(createCertifications());
        trainerDTO.setHourlyRate(50);
        trainerDTO.setShift("Morning");
        trainerDTO.setBiography("Certified personal trainer with a passion for helping clients reach their goals.");

        return trainerDTO;
    }

    private static List<ContactDetailsDTO> createContactDetails()
    {
        ContactDetailsDTO emailContact = new ContactDetailsDTO();
        emailContact.setContactType("Email");
        emailContact.setContact("john.doe@example.com");

        ContactDetailsDTO phoneContact = new ContactDetailsDTO();
        phoneContact.setContactType("Phone");
        phoneContact.setContact("+1 123 456 7890");

        return Arrays.asList(emailContact, phoneContact);
    }

    private static List<TrainerCertificationDTO> createCertifications()
    {
        TrainerCertificationDTO cert1 = new TrainerCertificationDTO();
        cert1.setCertificationName("Certified Strength and Conditioning Specialist (CSCS)");
        cert1.setIssuedFrom("NSCA");
        cert1.setDateIssued(new Date(120, 5, 1)); // June 1, 2020
        cert1.setValidUntil(new Date(125, 5, 1)); // June 1, 2025

        TrainerCertificationDTO cert2 = new TrainerCertificationDTO();
        cert2.setCertificationName("ACE Certified Personal Trainer");
        cert2.setIssuedFrom("ACE Fitness");
        cert2.setDateIssued(new Date(118, 2, 15)); // March 15, 2018
        cert2.setValidUntil(new Date(123, 2, 15)); // March 15, 2023

        return Arrays.asList(cert1, cert2);
    }
}
