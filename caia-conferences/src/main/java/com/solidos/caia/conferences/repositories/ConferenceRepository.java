package com.solidos.caia.conferences.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solidos.caia.conferences.entities.ConferenceEntity;

public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {
  @Query("SELECT c FROM ConferenceEntity c WHERE c.auditMetadata.deletedAt IS NULL")
  List<ConferenceEntity> findMany();

  @Query("SELECT c FROM ConferenceEntity c WHERE c.slug = ?1 AND c.auditMetadata.deletedAt IS NULL")
  Optional<ConferenceEntity> findBySlug(String slug);
}
