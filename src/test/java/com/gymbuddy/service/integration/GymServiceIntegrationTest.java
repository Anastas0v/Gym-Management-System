package com.gymbuddy.service.integration;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.repository.GymRepository;
import com.gymbuddy.service.GymService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

import static com.gymbuddy.service.integration.utils.GymDTOToClassMapper.mapGym;
import static com.gymbuddy.service.integration.utils.DateUtils.truncateToMinutes;

@SpringBootTest
@Transactional
public class GymServiceIntegrationTest
{
    @Autowired
    private GymService gymService;

    @Autowired
    private GymRepository gymRepository;

    @BeforeEach
    public void setup()
    {
        gymRepository.deleteAll();
        GymDTO gymDTO = mapGym();
        gymService.create(gymDTO);
    }

    @Test
    public void createAndSaveGym_Success()
    {
        GymDTO gymDTO = mapGym();
        Gym gym = gymService.create(gymDTO);

        LocalDateTime gymCreated = truncateToMinutes(gym.getCreated());
        LocalDateTime now = truncateToMinutes(LocalDateTime.now());

        Assertions.assertEquals(gymCreated, now);
        Assertions.assertNotNull(gym.getId());
    }

    @Test
    public void createAndSaveGym_Fail()
    {
        GymDTO gymDTO = mapGym();
        gymDTO.setGymName(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> gymService.create(gymDTO));
    }

    @Test
    public void testFindById()
    {
        Long id = gymRepository.findAll().getFirst().getId();
        Gym foundGym = gymService.findById(id);

        Assertions.assertNotNull(foundGym);
        Assertions.assertEquals(id, foundGym.getId());
        Assertions.assertEquals("TestGym", foundGym.getGymName());
    }

    @Test
    public void testDelete()
    {
        Long id = gymRepository.findAll().getFirst().getId();
        gymService.delete(id);

        Assertions.assertThrows(EntityNotFoundException.class, () -> gymService.findById(id));
    }
}
