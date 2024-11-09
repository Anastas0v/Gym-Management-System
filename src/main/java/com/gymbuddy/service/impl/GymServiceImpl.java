package com.gymbuddy.service.impl;

import com.gymbuddy.model.Gym;
import com.gymbuddy.repository.GymRepository;
import com.gymbuddy.service.GymService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymServiceImpl implements GymService
{
    private final GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository)
    {
        this.gymRepository = gymRepository;
    }

    @Override
    public List<Gym> findAll()
    {
        List<Gym> allGyms = gymRepository.findAll();
        return allGyms;
    }

    @Override
    public Gym findById(Long id)
    {
        Gym gym = gymRepository.findById(id).get();
        return gym;
    }

    @Override
    public Gym create(Long id, String name, String address, String city, String state, Integer zipCode, Integer phone, String email, String website, String description, String workingHours, Integer membership, boolean isGym24, Integer capacity) {
        if (id == null || name == null || name.isEmpty() || address == null || address.isEmpty() ||
                city == null || city.isEmpty() || state == null || state.isEmpty() || zipCode == null ||
                phone == null || email == null || email.isEmpty() || workingHours == null ||
                workingHours.isEmpty() || membership == null || capacity == null) {
            throw new IllegalArgumentException("All required fields must be provided.");
        }

        Gym gym = new Gym(id, name, address, city, state, zipCode, phone, email, website, description, workingHours, membership, isGym24, capacity);
        gymRepository.save(gym);
        return gym;
    }

    @Override
    public Gym update(Long id, Gym gym) {
        if (id == null || gym == null) {
            throw new IllegalArgumentException("ID and Gym details must be provided.");
        }

        Optional<Gym> existingGym = Optional.ofNullable(gymRepository.findById(id));

        if (existingGym.isPresent()) {
            Gym updatedGym = existingGym.get();

            // Update fields
            updatedGym.setName(gym.getName());
            updatedGym.setAddress(gym.getAddress());
            updatedGym.setCity(gym.getCity());
            updatedGym.setState(gym.getState());
            updatedGym.setZipCode(gym.getZipCode());
            updatedGym.setPhone(gym.getPhone());
            updatedGym.setEmail(gym.getEmail());
            updatedGym.setWebsite(gym.getWebsite());
            updatedGym.setDescription(gym.getDescription());
            updatedGym.setWorkingHours(gym.getWorkingHours());
            updatedGym.setMembership(gym.getMembership());
            updatedGym.setIsGym24(gym.isGym24());
            updatedGym.setCapacity(gym.getCapacity());

            // Save and return the updated Gym object
            return gymRepository.save(updatedGym);
        }

        return null;  // or throw an exception if not found
    }


    @Override
    public void delete(Long id)
    {
        if(id == null)
        {
            throw new IllegalArgumentException();
        }

        gymRepository.deleteById(id);
    }

}
