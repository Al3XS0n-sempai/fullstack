package com.fullstack.givegift.service;

import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.model.MyPasswordEncoder;
import com.fullstack.givegift.repository.GigiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private GigiRepository gigiRepository;

    @Autowired
    private MyPasswordEncoder passwordEncoder;

    public Optional<Gigi> createGigi(String username, String password) {
        Optional<Gigi> gigiOpt = gigiRepository.findByUsername(username);
        if (gigiOpt.isEmpty()) {
            Gigi gigi = new Gigi();
            gigi.setBalance(5000L);
            gigi.setUsername(username);
            gigi.setPassword(passwordEncoder.getPasswordEncoder().encode(password));

            gigiRepository.save(gigi);
            return Optional.of(gigi);
        }
        return Optional.empty();
    }
}
