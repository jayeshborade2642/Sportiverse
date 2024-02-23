package com.sportyverse.repository;

import com.sportyverse.entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
    Optional<Description> findByDescriptionId(Description descriptionId);
}
