package com.gymbuddy.service.integration;

import com.gymbuddy.model.Trainer;
import com.gymbuddy.model.dto.TrainerDTO;
import com.gymbuddy.repository.TrainerRepository;
import com.gymbuddy.service.TrainerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

import static com.gymbuddy.service.integration.utils.DateUtils.truncateToMinutes;
import static com.gymbuddy.service.integration.utils.TrainerDTOToClassMapper.mapTrainer;

@SpringBootTest
@Transactional
public class TrainerServiceIntegrationTest
{
    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerRepository trainerRepository;

    @BeforeEach
    public void setup()
    {
        trainerRepository.deleteAll();
        TrainerDTO trainerDTO = mapTrainer();
        trainerService.create(trainerDTO);
    }

    @Test
    public void createAndSaveTrainer_Success()
    {
        TrainerDTO trainerDTO = mapTrainer();
        Trainer trainer = trainerService.create(trainerDTO);

        LocalDateTime trainerCreated = truncateToMinutes(trainer.getCreated());
        LocalDateTime now = truncateToMinutes(LocalDateTime.now());

        Assertions.assertNotNull(trainer.getId());
        Assertions.assertEquals(trainerCreated, now);
    }

    @Test
    public void createAndSaveTrainer_Fail()
    {
        TrainerDTO trainerDTO = mapTrainer();
        trainerDTO.setFirstName(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> trainerService.create(trainerDTO));
    }

    @Test
    public void testFindById_Success()
    {
        Long id = trainerRepository.findAll().getFirst().getId();
        Trainer foundTrainer = trainerService.findById(id);

        Assertions.assertNotNull(foundTrainer);
        Assertions.assertEquals(id, foundTrainer.getId());
        Assertions.assertEquals("John", foundTrainer.getFirstName());
    }

    @Test
    public void testDeleteTrainer_Success()
    {
        Long id = trainerRepository.findAll().getFirst().getId();
        trainerService.delete(id);

        Assertions.assertThrows(EntityNotFoundException.class, () -> trainerService.findById(id));
    }
}
