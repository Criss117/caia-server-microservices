package com.solidos.caia.reviewers.domain.repositories;

import com.solidos.caia.reviewers.domain.entities.Reviewer;

public interface HttpUserRepository {
  Reviewer findByEmail(String email);
}
