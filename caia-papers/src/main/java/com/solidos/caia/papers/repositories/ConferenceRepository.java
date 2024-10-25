package com.solidos.caia.papers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.papers.entities.ConferenceEntity;

@Repository
public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {

  @Query("SELECT c FROM ConferenceEntity c WHERE c.slug = ?1 AND c.auditMetadata.deletedAt IS NULL")
  Optional<ConferenceEntity> findBySlug(String slug);
}
