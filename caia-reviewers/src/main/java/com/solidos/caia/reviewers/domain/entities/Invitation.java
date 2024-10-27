package com.solidos.caia.reviewers.domain.entities;

import java.time.LocalDateTime;

import com.solidos.caia.reviewers.domain.valueobjects.AuditMetadata;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationId;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationState;
import com.solidos.caia.reviewers.utils.TokenGenerator;

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

  private Conference conference;

  public void changeState(InvitationState state) {
    if (this.state == InvitationState.PENDING && state == InvitationState.PENDING) {
      throw new IllegalStateException("Cannot change state to PENDING");
    }

    if (this.state == InvitationState.PENDING && state != InvitationState.PENDING) {
      this.respondedAt = LocalDateTime.now();
      this.token = null;
    }

    this.state = state;
  }

  public void setToken() {
    this.token = TokenGenerator.generate();
  }

  public void prepareToSend() {
    this.setToken();
    this.changeState(InvitationState.PENDING);
  }
}
