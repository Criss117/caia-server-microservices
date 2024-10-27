package com.solidos.caia.reviewers.application.services;

import java.util.List;

import com.solidos.caia.reviewers.application.dtos.ResponseInvitationDto;
import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.domain.entities.Invitation;

public interface InvitationService {
  void sendInvitaion(SendInvitationDto sendInvitationDto);

  void responseInvitation(String userEmail, String token, ResponseInvitationDto responseInvitationDto);

  List<Invitation> findByReviewer(String userEmail);
}
