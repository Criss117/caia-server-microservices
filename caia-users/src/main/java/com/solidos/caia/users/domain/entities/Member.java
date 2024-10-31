package com.solidos.caia.users.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
  private Long conferenceId;
  private AuditMetadata auditMetadata;
}
