package com.fullstack.givegift.service;

import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.model.Placement;
import com.fullstack.givegift.model.PlacementCredentialRequest;
import com.fullstack.givegift.repository.GigiRepository;
import com.fullstack.givegift.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class PlacementService {
    @Autowired
    private PlacementRepository placementRepository;
    @Autowired
    private GigiRepository gigiRepository;

    public Optional<Placement> createPlacement(Gigi gigi, PlacementCredentialRequest request) {
        if (placementRepository.findByAuthor(gigi).size() == 3) {
            return Optional.empty();
        }
        Placement placement = new Placement();
        placement.setAuthor(gigi);
        placement.setName(request.getName());
        placement.setDescription(request.getDescription());
        placement.setTargetSum(request.getTargetSum());
        placement.setCollected(0L);

        return Optional.of(placementRepository.save(placement));
    }

    public boolean deletePlacement(Gigi gigi, Long placementId) {
        Optional<Placement> placement = placementRepository.findById(placementId);
        if (placement.isEmpty() || !placement.get().getAuthor().getId().equals(gigi.getId())) {
            return false;
        }
        Optional<Gigi> author = gigiRepository.findById(gigi.getId());
        if (author.isEmpty()) {
            return false;
        }
        Long curBalance = author.get().getBalance();
        author.get().setBalance(curBalance + placement.get().getCollected());
        placementRepository.deleteById(placementId);
        gigiRepository.save(author.get());
        return true;
    }

    public Set<Placement> findByAuthor(Gigi gigi) {
        return placementRepository.findByAuthor(gigi);
    }

    public Optional<Placement> findById(Long id) {
        return placementRepository.findById(id);
    }
}
