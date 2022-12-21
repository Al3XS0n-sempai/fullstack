package com.fullstack.givegift.service;

import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.model.Placement;
import com.fullstack.givegift.repository.GigiRepository;
import com.fullstack.givegift.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private PlacementRepository placementRepository;

    @Autowired
    private GigiRepository gigiRepository;

    public boolean makeDonation(Long placementId, Gigi donator, Long donationAmount) {
        if (donator.getBalance() >= donationAmount) {
            Optional<Placement> placementOpt = placementRepository.findById(placementId);
            if (placementOpt.isPresent()) {
                Placement placement = placementOpt.get();
                placement.setCollected(placement.getCollected() + donationAmount);
                placementRepository.save(placement);

                donator.setBalance(donator.getBalance() - donationAmount);
                gigiRepository.save(donator);

                return true;
            }
        }
        return false;
    }
}
