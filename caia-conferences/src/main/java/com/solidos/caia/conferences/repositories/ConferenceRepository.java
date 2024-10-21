package com.solidos.caia.conferences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidos.caia.conferences.entities.ConferenceEntity;

public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {
}
