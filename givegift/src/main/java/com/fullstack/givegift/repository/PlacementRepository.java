package com.fullstack.givegift.repository;

import com.fullstack.givegift.model.Gigi;
import com.fullstack.givegift.model.Placement;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.List;

public interface PlacementRepository extends CrudRepository<Placement, Long> {
    Set<Placement> findByAuthor(Gigi author);

    List<Placement> findAll();
}
