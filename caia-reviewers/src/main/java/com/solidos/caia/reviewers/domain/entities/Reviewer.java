package com.solidos.caia.reviewers.domain.entities;

import com.solidos.caia.reviewers.domain.valueobjects.AuditMetadata;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Reviewer {
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private String affiliation;

  private AuditMetadata auditMetadata;

  public void prepareToSave() {
    this.id = null;
  }
}
