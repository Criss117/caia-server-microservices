package com.solidos.caia.reviewers.domain.valueobjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InvitationId {
  private Long userId;
  private Long conferenceId;
}
