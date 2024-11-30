package com.gymbuddy.model.shared;

import com.gymbuddy.model.enumerations.ContactType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "contact_details")
public class ContactDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", nullable = false)
    private ContactType contactType;

    @Column(name = "contact_information", nullable = false)
    private String contact;
}
