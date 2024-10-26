package com.solidos.caia.reviewers.domain.repositories;

import com.solidos.caia.reviewers.domain.entities.Invitation;

public interface ReviewInvitationRepository {
  Invitation save(Invitation reviewInvitation);
}
