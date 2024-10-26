package com.solidos.caia.reviewers.infrastructure.entities;

import java.time.LocalDateTime;

import com.solidos.caia.reviewers.domain.valueobjects.InvitationState;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invitations")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvitationEntity {
  @EmbeddedId
  private InvitationComposeId invitationComposeId;

  // @ManyToOne
  // @JoinColumn(name = "conference_id", insertable = false, updatable = false)
  // private ConferenceEntity conferenceEntity;

  // @ManyToOne
  // @JoinColumn(name = "user_id", insertable = false, updatable = false)
  // private UserEntity userEntity;

  private String token;

  @Column(name = "state", nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private InvitationState state = InvitationState.PENDING;

  @Column(name = "responded_at")
  private LocalDateTime respondedAt;

  @Column(name = "message")
  private String message;

  @Embedded
  private EntityAuditMetadata auditMetadata;
}
