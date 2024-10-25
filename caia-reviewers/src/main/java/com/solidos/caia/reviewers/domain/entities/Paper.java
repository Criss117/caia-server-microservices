package com.solidos.caia.reviewers.domain.entities;

import com.solidos.caia.reviewers.domain.valueobjects.AuditMetadata;
import com.solidos.caia.reviewers.domain.valueobjects.PaperState;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Paper {
  private Long id;
  private String title;
  private String description;
  private String fileName;
  private String keys;
  private PaperState state;
  private AuditMetadata auditMetadata;

  public void changeState(PaperState state) {
    this.state = state;
  }
}
