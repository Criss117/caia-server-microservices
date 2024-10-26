package com.solidos.caia.reviewers.application.services.impl;

import org.springframework.stereotype.Service;

import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.application.mappers.ReviewInvitationMapper;
import com.solidos.caia.reviewers.application.services.ReviewInvitationService;
import com.solidos.caia.reviewers.domain.entities.ReviewInvitation;
import com.solidos.caia.reviewers.domain.repositories.ReviewInvitationRepository;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationState;

@Service
public class ReviewInvitationServiceImpl implements ReviewInvitationService {

  private final ReviewInvitationRepository reviewInvitationRepository;

  public ReviewInvitationServiceImpl(ReviewInvitationRepository reviewInvitationRepository) {
    this.reviewInvitationRepository = reviewInvitationRepository;
  }

  @Override
  public void sendInvitaion(SendInvitationDto sendInvitationDto) {
    ReviewInvitation reviewInvitation = ReviewInvitationMapper.dtoToDomain(sendInvitationDto);

    reviewInvitation.changeState(InvitationState.PENDING);

    reviewInvitationRepository.save(reviewInvitation);
  }

}
