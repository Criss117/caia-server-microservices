package com.solidos.caia.reviewers.application.mappers;

import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationId;

public class InvitationMapper {
  public static Invitation dtoToDomain(SendInvitationDto dto) {
    InvitationId id = InvitationId.builder()
        .userId(dto.getUserId())
        .conferenceId(dto.getConferenceId())
        .build();

    return Invitation.builder()
        .id(id)
        .message(dto.getMessage())
        .build();
  }
}
