package com.solidos.caia.reviewers.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.reviewers.infrastructure.entities.ReviewerEntity;

@Repository
public interface EntityReviewerRepository extends JpaRepository<ReviewerEntity, Long> {

  @Query("SELECT r FROM ReviewerEntity r WHERE r.email = ?1 AND r.auditMetadata.deletedAt IS NULL")
  Optional<ReviewerEntity> findByEmail(String email);
}
