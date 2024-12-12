package com.gymbuddy.web.api;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.service.GymService;
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
@RequestMapping("/gym")
public class GymAPIController
{
    @Autowired
    private GymService gymService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Gym>> getGym(@PathVariable Long id)
    {
        ApiResponse<Gym> response;
        try
        {
            Gym gym = gymService.findById(id);
            response = new ApiResponse<>(true, HttpStatus.OK.value(), gym, null);
            return ResponseEntity.status(200).body(response);
        }
        catch (EntityNotFoundException e)
        {
            response = new ApiResponse<>(false, HttpStatus.NOT_FOUND.value(), null, new ErrorDetails("Not Found", e.getMessage()));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping({"/", "/all"})
    public ResponseEntity<ApiResponse<List<Gym>>> getAll()
    {
        ApiResponse<List<Gym>> response;
        try
        {
            List<Gym> gyms = gymService.findAll();
            response = new ApiResponse<>(true, HttpStatus.OK.value(), gyms, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e)
        {
            response = new ApiResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, new ErrorDetails("Internal Error", e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Gym>> createGym(@RequestBody GymDTO gymDTO)
    {
        ApiResponse<Gym> response;
        try
        {
            Gym gym = gymService.create(gymDTO);
            response = new ApiResponse<>(true, HttpStatus.CREATED.value(), gym, null);
            return ResponseEntity.status(201).body(response);
        }
        catch (Exception e)
        {
            response = new ApiResponse<>(false, HttpStatus.BAD_REQUEST.value(), null, new ErrorDetails("Invalid Input", e.getMessage()));
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Gym>> updateGym(@RequestBody Gym gym)
    {
        ApiResponse<Gym> response;
        try
        {
            Gym updatedGym = gymService.update(gym);
            response = new ApiResponse<>(true, HttpStatus.OK.value(), updatedGym, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e)
        {
            response = new ApiResponse<>(false, HttpStatus.BAD_REQUEST.value(), null, new ErrorDetails("Invalid Input", e.getMessage()));
            return ResponseEntity.status(400).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGym(@PathVariable Long id)
    {
        ApiResponse<Void> response;
        try
        {
            gymService.delete(id);
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
