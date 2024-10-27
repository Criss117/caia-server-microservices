package com.solidos.caia.reviewers.application.services.impl;

import org.springframework.stereotype.Service;

import com.solidos.caia.reviewers.application.services.ReviewerService;
import com.solidos.caia.reviewers.domain.entities.Reviewer;
import com.solidos.caia.reviewers.domain.repositories.ReviewerRepository;

@Service
public class ReviewerServiceImpl implements ReviewerService {

  private final ReviewerRepository reviewerRepository;

  public ReviewerServiceImpl(ReviewerRepository reviewerRepository) {
    this.reviewerRepository = reviewerRepository;
  }

  @Override
  public Reviewer findById(Long id) {
    return reviewerRepository.findById(id);
  }

  @Override
  public Reviewer findByEmail(String email) {
    return reviewerRepository.findByEmail(email);
  }

  @Override
  public Reviewer save(Reviewer reviewer) {
    return reviewerRepository.save(reviewer);
  }

  @Override
  public Reviewer findLocalByEmail(String email) {
    return reviewerRepository.findLocalByEmail(email);
  }

}
