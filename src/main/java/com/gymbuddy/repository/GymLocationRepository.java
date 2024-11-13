package com.gymbuddy.repository;

import com.gymbuddy.model.shared.GymLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymLocationRepository extends JpaRepository<GymLocation, Long>
{
}
