package com.solidos.caia.reviewers.domain.entities;

import com.solidos.caia.reviewers.domain.valueobjects.AuditMetadata;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Conference {
  private Long id;

  private String name;

  private String slug;

  private AuditMetadata auditMetadata;
}
