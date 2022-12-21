package com.fullstack.givegift.controller;

import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.model.Placement;
import com.fullstack.givegift.service.RandomPlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/randomPlacement")
public class RandomPlacementController {
    @Autowired
    private RandomPlacementService randomPlacementService;

    @GetMapping("")
    public ResponseEntity<?> getRandomPlacement(@AuthenticationPrincipal Gigi gigi) {
        Optional<Placement> placementOpt = randomPlacementService.getRandomPlacement();

        return (placementOpt.isPresent() ? ResponseEntity.ok().body(placementOpt.get()) : ResponseEntity.badRequest().body(""));
    }
}
