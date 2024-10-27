package com.solidos.caia.reviewers.application.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.reviewers.application.dtos.ResponseInvitationDto;
import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.application.mappers.InvitationMapper;
import com.solidos.caia.reviewers.application.services.InvitationService;
import com.solidos.caia.reviewers.application.services.ReviewerService;
import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.entities.Reviewer;
import com.solidos.caia.reviewers.domain.repositories.InvitationRepository;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationId;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationState;

@Service
public class InvitationServiceImpl implements InvitationService {

  private final InvitationRepository invitationRepository;
  private final ReviewerService reviewerService;

  public InvitationServiceImpl(InvitationRepository invitationRepository, ReviewerService reviewerService) {
    this.invitationRepository = invitationRepository;
    this.reviewerService = reviewerService;
  }

  @Override
  public void sendInvitaion(SendInvitationDto sendInvitationDto) {
    Invitation invitation = invitationRepository.findById(InvitationId.builder()
        .userId(sendInvitationDto.getUserId())
        .conferenceId(sendInvitationDto.getConferenceId())
        .build());

    if (invitation != null && invitation.getState() == InvitationState.PENDING) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invitation already sent");
    }

    Invitation reviewInvitation = InvitationMapper.dtoToDomain(sendInvitationDto);
    try {

      reviewInvitation.prepareToSend();
      // TODO: send email
      invitationRepository.save(reviewInvitation);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error sending invitation");
    }
  }

  @Override
  public void responseInvitation(String userEmail, String token, ResponseInvitationDto responseInvitationDto) {
    Invitation reviewInvitation = invitationRepository.findByToken(token);

    if (responseInvitationDto.getState().equals(InvitationState.REJECTED)) {
      reviewInvitation.changeState(responseInvitationDto.getState());
      invitationRepository.save(reviewInvitation);
      return;
    }

    Reviewer reviewer = reviewerService.findByEmail(userEmail);

    if (reviewer.getId() != reviewInvitation.getId().getUserId()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not the reviewer");
    }

    reviewInvitation.changeState(responseInvitationDto.getState());

    reviewerService.save(reviewer);
    invitationRepository.save(reviewInvitation);

    // TODO: send email
  }

}
