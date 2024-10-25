package com.solidos.caia.reviewers.domain.valueobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationId {
  private Long userId;
  private Long conferenceId;
}
