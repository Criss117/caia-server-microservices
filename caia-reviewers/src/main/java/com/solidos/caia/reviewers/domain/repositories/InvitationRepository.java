package com.solidos.caia.reviewers.domain.repositories;

import com.solidos.caia.reviewers.domain.entities.Invitation;

public interface InvitationRepository {
  Invitation save(Invitation reviewInvitation);

  Invitation findByToken(String token);
}
