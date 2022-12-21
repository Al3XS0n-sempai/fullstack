package com.fullstack.givegift.repository;

import com.fullstack.givegift.model.Gigi;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GigiRepository extends CrudRepository<Gigi, Long> {
    Optional<Gigi> findByUsername(String username);
}
