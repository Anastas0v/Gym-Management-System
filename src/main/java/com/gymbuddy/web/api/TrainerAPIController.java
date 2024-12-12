package com.gymbuddy.web.api;

import com.gymbuddy.model.Trainer;
import com.gymbuddy.model.dto.TrainerDTO;
import com.gymbuddy.service.TrainerService;
import com.gymbuddy.web.api.response.ApiResponse;
import com.gymbuddy.web.api.response.ErrorDetails;
import jakarta.persistence.EntityNotFoundException;
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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Trainer>> getTrainer(@PathVariable Long id)
    {
        ApiResponse<Trainer> response;
        try
        {
            Trainer trainer = getTrainerService().findById(id);
            response = new ApiResponse<>(true, HttpStatus.OK.value(), trainer, null);
            return ResponseEntity.status(200).body(response);
        }
        catch (EntityNotFoundException e)
        {
            response = new ApiResponse<>(false, HttpStatus.NOT_FOUND.value(), null, new ErrorDetails("Not Found", e.getMessage()));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping({"/", "/all"})
    public ResponseEntity<ApiResponse<List<Trainer>>> getAll()
    {
        ApiResponse<List<Trainer>> response;
        try
        {
            List<Trainer> trainers = getTrainerService().findAll();
            response = new ApiResponse<>(true, HttpStatus.OK.value(), trainers, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e)
            {
                response = new ApiResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDetails("Internal Error", e.getMessage()));
                return ResponseEntity.status(500).body(response);
            }
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Trainer>> createTrainer(@RequestBody TrainerDTO trainerDTO)
    {
        ApiResponse<Trainer> response;
        try
        {
            Trainer trainer = getTrainerService().create(trainerDTO);
            response = new ApiResponse<>(true, HttpStatus.CREATED.value(), trainer, null);
            return ResponseEntity.status(201).body(response);
        }
        catch (Exception e)
        {
            response = new ApiResponse<>(false, HttpStatus.BAD_REQUEST.value(), null, new ErrorDetails("Invalid Input", e.getMessage()));
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Trainer>> updateTrainer(@RequestBody Trainer trainer)
    {
        ApiResponse<Trainer> response;
        try
        {
            Trainer updatedTrainer = getTrainerService().update(trainer);
            response = new ApiResponse<>(true, HttpStatus.OK.value(), updatedTrainer, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e)
        {
            response = new ApiResponse<>(false, HttpStatus.BAD_REQUEST.value(), null, new ErrorDetails("Invalid Input", e.getMessage()));
            return ResponseEntity.status(400).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTrainer(@PathVariable Long id)
    {
        ApiResponse<Void> response;
        try
        {
            getTrainerService().delete(id);
            response = new ApiResponse<>(true, HttpStatus.OK.value(), null, null);
            return ResponseEntity.status(200).body(response);
        }
        catch (EntityNotFoundException e)
        {
            response = new ApiResponse<>(false, HttpStatus.NOT_FOUND.value(), null, new ErrorDetails("Not Found", e.getMessage()));
            return ResponseEntity.status(404).body(response);
        }
        catch (Exception e)
        {
            response = new ApiResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDetails("Internal Error", e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
    }
}
