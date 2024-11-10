package com.gymbuddy.model;

import com.gymbuddy.model.enumerations.ContactType;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "contact_details")
@Data
public class ContactDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", nullable = false)
    private ContactType contactType;

    //if is phone number it will require regex validation, email and social media also
    @Column(name = "contact_information", nullable = false)
    private String contact;
}
