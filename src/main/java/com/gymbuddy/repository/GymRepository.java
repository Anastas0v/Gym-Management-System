package com.gymbuddy.repository;

import com.gymbuddy.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer>
{
    Gym findById(Long id);

    void deleteById(Long id);
}
