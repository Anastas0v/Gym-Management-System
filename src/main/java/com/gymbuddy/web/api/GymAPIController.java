package com.gymbuddy.web.api;

import com.gymbuddy.model.Gym;
import com.gymbuddy.model.dto.GymDTO;
import com.gymbuddy.service.GymService;
import com.gymbuddy.web.api.response.ApiResponse;
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
        Gym gym = getGymService().findById(id);
        ApiResponse<Gym> response = new ApiResponse<>(gym, HttpStatus.OK.value(), true);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"/", "/all"})
    public ResponseEntity<ApiResponse<List<Gym>>> getAll()
    {
        List<Gym> gyms = getGymService().findAll();
        ApiResponse<List<Gym>> response = new ApiResponse<>(gyms, HttpStatus.OK.value(), true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Gym>> createGym(@RequestBody GymDTO gymDTO)
    {
        Gym gym = getGymService().create(gymDTO);
        ApiResponse<Gym> response = new ApiResponse<>(gym, HttpStatus.CREATED.value(), true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Gym>> updateGym(@RequestBody Gym gym)
    {
        Gym updated = getGymService().update(gym);
        ApiResponse<Gym> response = new ApiResponse<>(updated, HttpStatus.OK.value(), true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGym(@PathVariable Long id)
    {
        gymService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(null, HttpStatus.OK.value(), true);
        return ResponseEntity.ok(response);
    }
}
