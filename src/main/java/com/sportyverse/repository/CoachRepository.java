package com.sportyverse.repository;

import com.sportyverse.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    Optional<Coach> findBySport(String sport);

    Coach findByEmail(String email);

    Optional<Coach> findOneByEmailAndPassword(String email, String password);
}
