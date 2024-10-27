package com.solidos.caia.reviewers.infrastructure.repositories;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.reviewers.domain.entities.Reviewer;
import com.solidos.caia.reviewers.domain.repositories.ReviewerRepository;
import com.solidos.caia.reviewers.infrastructure.entities.ReviewerEntity;
import com.solidos.caia.reviewers.infrastructure.mappers.ReviewerEntityMapper;

@Repository
public class ReviewerRpositoryImpl implements ReviewerRepository {

  private final EntityReviewerRepository entityReviewerRepository;
  private final HttpUserRepository httpUserRepository;

  public ReviewerRpositoryImpl(EntityReviewerRepository entityReviewerRepository,
      HttpUserRepository httpUserRepository) {
    this.entityReviewerRepository = entityReviewerRepository;
    this.httpUserRepository = httpUserRepository;
  }

  @Override
  public Reviewer findByEmail(String email) {
    Optional<ReviewerEntity> entityReviewer = entityReviewerRepository.findByEmail(email);

    if (entityReviewer.isPresent()) {
      return ReviewerEntityMapper.entityToDomain(entityReviewer.get());
    }

    var user = httpUserRepository.findByEmail(email).getBody();

    if (user == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    return user.getData();
  }

  @Override
  public Reviewer findById(Long Id) {
    ReviewerEntity entityReviewer = entityReviewerRepository.findById(Id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reviewer not found"));

    return ReviewerEntityMapper.entityToDomain(entityReviewer);
  }

  @Override
  public Reviewer save(Reviewer reviewer) {
    ReviewerEntity entity = ReviewerEntityMapper.domainToEntity(reviewer);
    entity = entityReviewerRepository.save(entity);
    return ReviewerEntityMapper.entityToDomain(entity);
  }

}
