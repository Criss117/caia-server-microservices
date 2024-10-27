package com.solidos.caia.reviewers.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solidos.caia.reviewers.infrastructure.entities.InvitationComposeId;
import com.solidos.caia.reviewers.infrastructure.entities.InvitationEntity;

public interface EntityInvitationRepository extends JpaRepository<InvitationEntity, InvitationComposeId> {
  @Query("SELECT i FROM InvitationEntity i WHERE i.token = ?1 AND i.auditMetadata.deletedAt IS NULL")
  Optional<InvitationEntity> findInvitationByToken(String token);

  @Query("SELECT i FROM InvitationEntity i WHERE i.invitationComposeId.userId = ?1 AND i.invitationComposeId.conferenceId = ?2 AND i.auditMetadata.deletedAt IS NULL")
  Optional<InvitationEntity> findById(Long userId, Long conferenceId);

  @Query("SELECT i FROM InvitationEntity i WHERE i.invitationComposeId.userId = ?1 AND i.auditMetadata.deletedAt IS NULL")
  List<InvitationEntity> findByReviewerId(Long reviewerId);
}
