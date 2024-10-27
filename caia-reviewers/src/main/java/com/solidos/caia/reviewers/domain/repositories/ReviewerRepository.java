package com.solidos.caia.reviewers.domain.repositories;

import com.solidos.caia.reviewers.domain.entities.Reviewer;

public interface ReviewerRepository {
  public Reviewer findByEmail(String email);

  public Reviewer findById(Long Id);

  public Reviewer save(Reviewer reviewer);
}
