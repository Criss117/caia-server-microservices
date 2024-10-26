package com.solidos.caia.reviewers.infrastructure.mappers;

import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.valueobjects.AuditMetadata;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationId;
import com.solidos.caia.reviewers.infrastructure.entities.EntityAuditMetadata;
import com.solidos.caia.reviewers.infrastructure.entities.InvitationComposeId;
import com.solidos.caia.reviewers.infrastructure.entities.InvitationEntity;

public class InvitationMapper {
  public static InvitationEntity domainToEntity(Invitation reviewInvitation) {

    InvitationComposeId id = InvitationComposeId.builder()
        .userId(reviewInvitation.getId().getUserId())
        .conferenceId(reviewInvitation.getId().getConferenceId())
        .build();

    EntityAuditMetadata entityAuditMetadata;
    if (reviewInvitation.getAuditMetadata() == null) {
      entityAuditMetadata = EntityAuditMetadata.builder().build();
    } else {
      entityAuditMetadata = EntityAuditMetadata.builder()
          .createdAt(reviewInvitation.getAuditMetadata().getCreatedAt())
          .updatedAt(reviewInvitation.getAuditMetadata().getUpdatedAt())
          .deletedAt(reviewInvitation.getAuditMetadata().getDeletedAt())
          .build();
    }

    return InvitationEntity.builder()
        .invitationComposeId(id)
        .token(reviewInvitation.getToken())
        .state(reviewInvitation.getState())
        .respondedAt(reviewInvitation.getRespondedAt())
        .message(reviewInvitation.getMessage())
        .auditMetadata(entityAuditMetadata)
        .build();
  }

  public static Invitation entityToDomain(InvitationEntity entity) {
    InvitationId id = InvitationId.builder()
        .userId(entity.getInvitationComposeId().getUserId())
        .conferenceId(entity.getInvitationComposeId().getConferenceId())
        .build();

    AuditMetadata auditMetadata = AuditMetadata.builder()
        .createdAt(entity.getAuditMetadata().getCreatedAt())
        .updatedAt(entity.getAuditMetadata().getUpdatedAt())
        .deletedAt(entity.getAuditMetadata().getDeletedAt())
        .build();

    return Invitation.builder()
        .id(id)
        .token(entity.getToken())
        .state(entity.getState())
        .respondedAt(entity.getRespondedAt())
        .message(entity.getMessage())
        .auditMetadata(auditMetadata)
        .build();
  }
}
