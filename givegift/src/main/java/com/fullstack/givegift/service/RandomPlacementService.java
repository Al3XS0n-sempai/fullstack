package com.fullstack.givegift.service;

import com.fullstack.givegift.model.Placement;
import com.fullstack.givegift.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RandomPlacementService {
    @Autowired
    private PlacementRepository placementRepository;

    public Optional<Placement> getRandomPlacement() {
        List<Placement> allPlacements = placementRepository.findAll();
        if (allPlacements.size() == 0) {
            return Optional.empty();
        }
        Random randomizer = new Random();
        Placement randomPlacement = allPlacements.get(randomizer.nextInt(allPlacements.size()));

        return Optional.of(randomPlacement);
    }
}
