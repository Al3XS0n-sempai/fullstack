package com.fullstack.givegift.controller;

import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.model.Placement;
import com.fullstack.givegift.model.PlacementCredentialRequest;
import com.fullstack.givegift.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/placements")
public class PlacementController {

    @Autowired
    private PlacementService placementService;
    @PostMapping("")
    public ResponseEntity<?> createPlacement(@AuthenticationPrincipal Gigi gigi,
                                             @RequestBody PlacementCredentialRequest request) {
        Optional<Placement> newPlacement = placementService.createPlacement(gigi, request);

        return (newPlacement.isPresent() ? ResponseEntity.ok(newPlacement) : ResponseEntity.badRequest().body(""));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePlacement(@AuthenticationPrincipal Gigi gigi,
                                             @PathVariable Long id) {
        boolean success = placementService.deletePlacement(gigi, id);

        return (success ? ResponseEntity.ok("") : ResponseEntity.badRequest().body(""));
    }

    @GetMapping("")
    public ResponseEntity<?> getPlacements(@AuthenticationPrincipal Gigi gigi) {
        Set<Placement> placements = placementService.findByAuthor(gigi);
        return ResponseEntity.ok().body(placements);
    }

    @GetMapping("{placementId}")
    public ResponseEntity<?> getPlacements(@PathVariable Long placementId,  @AuthenticationPrincipal Gigi gigi) {
        Optional<Placement> placementOpt = placementService.findById(placementId);
        return ResponseEntity.ok().body(placementOpt.orElse(new Placement()));
    }
}
