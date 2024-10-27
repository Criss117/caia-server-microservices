package com.solidos.caia.reviewers.application.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SendInvitationDto {
  @NotNull(message = "User cannot be null")
  private String email;

  @NotNull(message = "Conference cannot be null")
  private Long conferenceId;

  @NotNull(message = "Message cannot be null")
  @Size(max = 225, message = "Message must be less than 225 characters")
  private String message;
}
