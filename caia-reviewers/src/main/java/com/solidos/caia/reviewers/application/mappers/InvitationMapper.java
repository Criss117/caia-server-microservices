package com.solidos.caia.reviewers.application.mappers;

import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationId;

public class InvitationMapper {
  public static Invitation dtoToDomain(Long userId, SendInvitationDto sendInvitationDto) {
    InvitationId id = InvitationId.builder()
        .userId(userId)
        .conferenceId(sendInvitationDto.getConferenceId())
        .build();

    return Invitation.builder()
        .id(id)
        .message(sendInvitationDto.getMessage())
        .build();
  }
}
