package com.gymbuddy.web.api;

import com.gymbuddy.model.Trainer;
import com.gymbuddy.model.dto.TrainerDTO;
import com.gymbuddy.service.TrainerService;
import com.gymbuddy.web.api.response.ApiResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RestController
@RequestMapping("/trainer")
public class TrainerAPIController
{
    @Autowired
    private TrainerService trainerService;

/*    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Trainer>> getTrainer(@PathVariable Long id)
    {
        Trainer trainer = getTrainerService().findById(id);
        ApiResponse<Trainer> response = new ApiResponse<>(trainer, HttpStatus.OK.value(), true);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"/", "/all"})
    public ResponseEntity<ApiResponse<List<Trainer>>> getAll()
    {
        List<Trainer> trainers = getTrainerService().findAll();
        ApiResponse<List<Trainer>> response = new ApiResponse<>(trainers, HttpStatus.OK.value(), true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Trainer>> createTrainer(@RequestBody TrainerDTO trainerDTO)
    {
        Trainer trainer = getTrainerService().create(trainerDTO);
        ApiResponse<Trainer> response = new ApiResponse<>(trainer, HttpStatus.CREATED.value(), true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Trainer>> updateTrainer(@RequestBody Trainer trainer)
    {
        Trainer updated = getTrainerService().update(trainer);
        ApiResponse<Trainer> response = new ApiResponse<>(updated, HttpStatus.OK.value(), true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTrainer(@PathVariable Long id)
    {
        getTrainerService().delete(id);
        ApiResponse<Void> response = new ApiResponse<>(null, HttpStatus.OK.value(), true);
        return ResponseEntity.ok(response);
    }*/
}
