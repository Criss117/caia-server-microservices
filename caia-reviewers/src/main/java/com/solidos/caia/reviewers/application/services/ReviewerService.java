package com.solidos.caia.reviewers.application.services;

import com.solidos.caia.reviewers.domain.entities.Reviewer;

public interface ReviewerService {
  public Reviewer findByEmail(String email);

  public Reviewer findById(Long id);

  public Reviewer save(Reviewer reviewer);

  public Reviewer findLocalByEmail(String email);
}
