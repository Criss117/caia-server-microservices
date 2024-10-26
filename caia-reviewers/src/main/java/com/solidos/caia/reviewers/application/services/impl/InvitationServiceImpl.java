package com.solidos.caia.reviewers.application.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.reviewers.application.dtos.ResponseInvitationDto;
import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.application.mappers.InvitationMapper;
import com.solidos.caia.reviewers.application.services.InvitationService;
import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.repositories.InvitationRepository;

@Service
public class InvitationServiceImpl implements InvitationService {

  private final InvitationRepository reviewInvitationRepository;

  public InvitationServiceImpl(InvitationRepository reviewInvitationRepository) {
    this.reviewInvitationRepository = reviewInvitationRepository;
  }

  @Override
  public void sendInvitaion(SendInvitationDto sendInvitationDto) {
    Invitation reviewInvitation = InvitationMapper.dtoToDomain(sendInvitationDto);
    try {

      reviewInvitation.prepareToSend();
      // TODO: send email
      reviewInvitationRepository.save(reviewInvitation);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error sending invitation");
    }
  }

  @Override
  public void responseInvitation(String token, ResponseInvitationDto responseInvitationDto) {
    Invitation reviewInvitation = reviewInvitationRepository.findByToken(token);
    reviewInvitation.changeState(responseInvitationDto.getState());
    // TODO: send email
    reviewInvitationRepository.save(reviewInvitation);
  }

}
