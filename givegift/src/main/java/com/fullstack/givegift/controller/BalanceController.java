package com.fullstack.givegift.controller;

import com.fullstack.givegift.model.BalanceCredential;
import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("")
    public ResponseEntity<?> getBalance(@AuthenticationPrincipal Gigi gigi) {
        BalanceCredential balanceCredential = balanceService.getBalance(gigi);
        return ResponseEntity.ok().body(balanceCredential);
    }

    @PostMapping("")
    public ResponseEntity<?> addBalance(@AuthenticationPrincipal Gigi gigi, @RequestBody BalanceCredential add) {
        boolean success = balanceService.addBalance(gigi, add.getBalance());
        return (success ? ResponseEntity.ok().body("") : ResponseEntity.badRequest().body(""));
    }
}
