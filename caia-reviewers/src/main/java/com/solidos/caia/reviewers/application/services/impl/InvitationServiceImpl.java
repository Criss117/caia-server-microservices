package com.solidos.caia.reviewers.application.services.impl;

import java.util.List;

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

import jakarta.transaction.Transactional;

@Service
public class InvitationServiceImpl implements InvitationService {

  private final InvitationRepository invitationRepository;
  private final ReviewerService reviewerService;

  public InvitationServiceImpl(InvitationRepository invitationRepository, ReviewerService reviewerService) {
    this.invitationRepository = invitationRepository;
    this.reviewerService = reviewerService;
  }

  @Override
  @Transactional
  public void sendInvitaion(SendInvitationDto sendInvitationDto) {
    Reviewer reviewer = reviewerService.findByEmail(sendInvitationDto.getEmail());

    reviewer.prepareToSave();

    Reviewer savedReviewer;

    try {
      savedReviewer = reviewerService.save(reviewer);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error sending invitation");
    }

    Invitation reviewInvitation = InvitationMapper.dtoToDomain(savedReviewer.getId(), sendInvitationDto);

    try {
      reviewInvitation.prepareToSend();
      invitationRepository.save(reviewInvitation);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error sending invitation");
    }
  }

  @Override
  public void responseInvitation(String userEmail, String token, ResponseInvitationDto responseInvitationDto) {
    Invitation reviewInvitation = invitationRepository.findByToken(token);

    reviewInvitation.changeState(responseInvitationDto.getState());
    invitationRepository.save(reviewInvitation);
    return;
  }

  @Override
  public List<Invitation> findByReviewer(String userEmail) {
    Reviewer reviewer = reviewerService.findByEmail(userEmail);

    return invitationRepository.findByReviewerId(reviewer.getId());
  }

}
