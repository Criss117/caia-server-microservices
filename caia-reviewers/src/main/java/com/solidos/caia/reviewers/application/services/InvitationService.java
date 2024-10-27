package com.solidos.caia.reviewers.application.services;

import com.solidos.caia.reviewers.application.dtos.ResponseInvitationDto;
import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;

public interface InvitationService {
  void sendInvitaion(SendInvitationDto sendInvitationDto);

  void responseInvitation(String userEmail, String token, ResponseInvitationDto responseInvitationDto);
}
