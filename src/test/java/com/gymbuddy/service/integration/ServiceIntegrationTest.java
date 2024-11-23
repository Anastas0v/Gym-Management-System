package com.gymbuddy.service.integration;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.service.GymService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

import static com.gymbuddy.service.integration.utils.DTOToClassMapper.mapGym;
import static com.gymbuddy.service.integration.utils.DateUtils.truncateToMinutes;

@SpringBootTest
@Transactional
public class ServiceIntegrationTest
{
    @Autowired
    private GymService gymService;

    @Test
    public void createAndSaveGym_SUCCESS()
    {
        GymDTO gymDTO = mapGym();
        Gym gym = gymService.create(gymDTO);

        LocalDateTime gymCreated = truncateToMinutes(gym.getCreated());
        LocalDateTime now = truncateToMinutes(LocalDateTime.now());

        // Assertion
        Assertions.assertEquals(gymCreated, now);
        Assertions.assertNotNull(gym.getId());
    }

    @Test
    public void createAndSaveGym_FAIL()
    {
        GymDTO gymDTO = mapGym();
        gymDTO.setGymName(null);

        // Assertion
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> gymService.create(gymDTO));

    }
}
