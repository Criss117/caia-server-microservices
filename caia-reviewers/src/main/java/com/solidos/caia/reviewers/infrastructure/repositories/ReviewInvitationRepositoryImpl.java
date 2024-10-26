package com.solidos.caia.reviewers.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import com.solidos.caia.reviewers.domain.entities.ReviewInvitation;
import com.solidos.caia.reviewers.domain.repositories.ReviewInvitationRepository;
import com.solidos.caia.reviewers.infrastructure.entities.InvitationEntity;
import com.solidos.caia.reviewers.infrastructure.mappers.ReviewInvitationMapper;

import jakarta.transaction.Transactional;

@Repository
public class ReviewInvitationRepositoryImpl implements ReviewInvitationRepository {

  private final EntityInvitationRepository entityInvitationRepository;

  public ReviewInvitationRepositoryImpl(EntityInvitationRepository entityInvitationRepository) {
    this.entityInvitationRepository = entityInvitationRepository;
  }

  @Override
  @Transactional
  public ReviewInvitation save(ReviewInvitation reviewInvitation) {

    InvitationEntity entity = entityInvitationRepository.save(ReviewInvitationMapper.domainToEntity(reviewInvitation));

    return ReviewInvitationMapper.entityToDomain(entity);
  }

}
