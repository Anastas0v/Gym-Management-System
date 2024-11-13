package com.gymbuddy.repository;

import com.gymbuddy.model.shared.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long>
{
}
