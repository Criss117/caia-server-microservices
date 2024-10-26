package com.solidos.caia.reviewers.domain.repositories;

import com.solidos.caia.reviewers.domain.entities.ReviewInvitation;

public interface ReviewInvitationRepository {
  ReviewInvitation save(ReviewInvitation reviewInvitation);
}
