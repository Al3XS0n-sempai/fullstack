package com.fullstack.givegift.controller;

import com.fullstack.givegift.model.DonationCredentialRequest;
import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/donations")
public class DonationController {
    @Autowired
    private DonationService donationService;

    @PostMapping("")
    public ResponseEntity<?> makeDonation(@AuthenticationPrincipal Gigi gigi,
                                          @RequestBody DonationCredentialRequest donationCredentialRequest) {
        boolean success = donationService.makeDonation(donationCredentialRequest.getPlacementId(),
                gigi, donationCredentialRequest.getAmount());
        if (success) {
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не хватает средств");
    }
}
