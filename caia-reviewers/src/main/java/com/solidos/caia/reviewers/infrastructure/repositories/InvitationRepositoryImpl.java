package com.solidos.caia.reviewers.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.repositories.ReviewInvitationRepository;
import com.solidos.caia.reviewers.infrastructure.entities.InvitationEntity;
import com.solidos.caia.reviewers.infrastructure.mappers.InvitationMapper;

import jakarta.transaction.Transactional;

@Repository
public class InvitationRepositoryImpl implements ReviewInvitationRepository {

  private final EntityInvitationRepository entityInvitationRepository;

  public InvitationRepositoryImpl(EntityInvitationRepository entityInvitationRepository) {
    this.entityInvitationRepository = entityInvitationRepository;
  }

  @Override
  @Transactional
  public Invitation save(Invitation reviewInvitation) {

    InvitationEntity entity = entityInvitationRepository.save(InvitationMapper.domainToEntity(reviewInvitation));

    return InvitationMapper.entityToDomain(entity);
  }

}
