package com.solidos.caia.reviewers.application.dtos;

import com.solidos.caia.reviewers.domain.valueobjects.InvitationState;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResponseInvitationDto {
  @NotNull(message = "State cannot be null")
  private InvitationState state;
}
