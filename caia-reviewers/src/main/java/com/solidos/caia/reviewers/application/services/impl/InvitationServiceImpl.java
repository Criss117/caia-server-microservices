package com.solidos.caia.reviewers.application.services.impl;

import org.springframework.stereotype.Service;

import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.application.mappers.InvitationMapper;
import com.solidos.caia.reviewers.application.services.InvitationService;
import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.repositories.ReviewInvitationRepository;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationState;

@Service
public class InvitationServiceImpl implements InvitationService {

  private final ReviewInvitationRepository reviewInvitationRepository;

  public InvitationServiceImpl(ReviewInvitationRepository reviewInvitationRepository) {
    this.reviewInvitationRepository = reviewInvitationRepository;
  }

  @Override
  public void sendInvitaion(SendInvitationDto sendInvitationDto) {
    Invitation reviewInvitation = InvitationMapper.dtoToDomain(sendInvitationDto);

    reviewInvitation.changeState(InvitationState.PENDING);

    reviewInvitationRepository.save(reviewInvitation);
  }

}
