package com.fullstack.givegift.service;

import com.fullstack.givegift.model.BalanceCredential;
import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.repository.GigiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceService {
    @Autowired
    private GigiRepository gigiRepository;

    public BalanceCredential getBalance(Gigi gigi) {
        Optional<Gigi> target = gigiRepository.findById(gigi.getId());
        BalanceCredential cred = new BalanceCredential();
        if (target.isEmpty()) {
            return null;
        }
        cred.setBalance(target.get().getBalance());
        return cred;
    }

    public boolean addBalance(Gigi gigi, Long addValue) {
        Optional<Gigi> target = gigiRepository.findById(gigi.getId());
        if (target.isEmpty()) {
            return false;
        }
        Long current = target.get().getBalance();
        target.get().setBalance(current + addValue);
        gigiRepository.save(target.get());
        return true;
    }

}
