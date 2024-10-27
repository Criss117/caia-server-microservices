package com.solidos.caia.papers.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.papers.entities.PaperEntity;

@Repository
public interface PaperRepository extends JpaRepository<PaperEntity, Long> {

  @Query("SELECT p FROM PaperEntity p WHERE p.authorEntity.id = ?1 AND p.auditMetadata.deletedAt IS NULL")
  List<PaperEntity> findByAuthorId(Long authorId);

  @Query("SELECT p FROM PaperEntity p WHERE p.id = ?1 AND p.auditMetadata.deletedAt IS NULL")
  Optional<PaperEntity> findPaper(Long id);

  @Query("SELECT p FROM PaperEntity p WHERE p.conferenceEntity.id = ?1 AND p.auditMetadata.deletedAt IS NULL")
  List<PaperEntity> findPapersByConference(Long conferenceId);
}
