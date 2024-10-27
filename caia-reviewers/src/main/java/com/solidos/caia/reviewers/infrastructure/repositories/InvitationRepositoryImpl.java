package com.solidos.caia.reviewers.infrastructure.repositories;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.repositories.InvitationRepository;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationId;
import com.solidos.caia.reviewers.infrastructure.entities.InvitationEntity;
import com.solidos.caia.reviewers.infrastructure.mappers.InvitationEntityMapper;

import jakarta.transaction.Transactional;

@Repository
public class InvitationRepositoryImpl implements InvitationRepository {

  private final EntityInvitationRepository entityInvitationRepository;

  public InvitationRepositoryImpl(EntityInvitationRepository entityInvitationRepository) {
    this.entityInvitationRepository = entityInvitationRepository;
  }

  @Override
  @Transactional
  public Invitation save(Invitation reviewInvitation) {
    InvitationEntity entity = entityInvitationRepository.save(InvitationEntityMapper.domainToEntity(reviewInvitation));

    return InvitationEntityMapper.entityToDomain(entity);
  }

  @Override
  public Invitation findByToken(String token) {
    InvitationEntity invitationEntity = entityInvitationRepository
        .findInvitationByToken(token).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invitation not found"));

    return InvitationEntityMapper.entityToDomain(invitationEntity);
  }

  @Override
  public Invitation findById(InvitationId id) {
    InvitationEntity invitationEntity = entityInvitationRepository
        .findById(id.getUserId(), id.getConferenceId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invitation not found"));

    return InvitationEntityMapper.entityToDomain(invitationEntity);
  }

  @Override
  public List<Invitation> findByReviewerId(Long reviewerId) {
    List<InvitationEntity> invitationEntities = entityInvitationRepository
        .findByReviewerId(reviewerId);

    return invitationEntities.stream()
        .map(InvitationEntityMapper::entityToDomain)
        .toList();
  }

}
