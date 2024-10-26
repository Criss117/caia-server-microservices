package com.solidos.caia.reviewers.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidos.caia.reviewers.infrastructure.entities.InvitationComposeId;
import com.solidos.caia.reviewers.infrastructure.entities.InvitationEntity;

public interface EntityInvitationRepository extends JpaRepository<InvitationEntity, InvitationComposeId> {

}
