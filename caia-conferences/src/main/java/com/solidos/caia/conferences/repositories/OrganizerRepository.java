package com.solidos.caia.conferences.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solidos.caia.conferences.entities.OrganizerEntity;

public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Long> {

  @Query("SELECT o FROM OrganizerEntity o WHERE o.email = ?1 AND o.auditMetadata.deletedAt IS NULL")
  Optional<OrganizerEntity> findByEmail(String email);
}
