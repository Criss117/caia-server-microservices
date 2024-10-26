package com.solidos.caia.reviewers.domain.entities;

import java.time.LocalDateTime;

import com.solidos.caia.reviewers.domain.valueobjects.AuditMetadata;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationId;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationState;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Invitation {
  private InvitationId id;

  private String token;

  private InvitationState state;

  private String message;

  private LocalDateTime respondedAt;

  private AuditMetadata auditMetadata;

  public void changeState(InvitationState state) {
    this.state = state;
    this.respondedAt = LocalDateTime.now();
  }
}
