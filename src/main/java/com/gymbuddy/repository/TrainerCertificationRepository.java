package com.gymbuddy.repository;

import com.gymbuddy.model.shared.TrainerCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerCertificationRepository extends JpaRepository<TrainerCertification, Long>
{
}
