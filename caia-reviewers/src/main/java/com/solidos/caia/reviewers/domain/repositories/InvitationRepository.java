package com.solidos.caia.reviewers.domain.repositories;

import java.util.List;

import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.domain.valueobjects.InvitationId;

public interface InvitationRepository {
  Invitation save(Invitation reviewInvitation);

  Invitation findByToken(String token);

  Invitation findById(InvitationId id);

  List<Invitation> findByReviewerId(Long reviewerId);
}
