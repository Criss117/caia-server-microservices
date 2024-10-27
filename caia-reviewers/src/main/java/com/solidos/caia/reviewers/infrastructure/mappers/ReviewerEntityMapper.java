package com.solidos.caia.reviewers.infrastructure.mappers;

import com.solidos.caia.reviewers.domain.entities.Reviewer;
import com.solidos.caia.reviewers.infrastructure.entities.ReviewerEntity;

public class ReviewerEntityMapper {

  public static Reviewer entityToDomain(ReviewerEntity entity) {
    return Reviewer.builder()
        .id(entity.getId() == null ? null : entity.getId())
        .firstName(entity.getFirstName())
        .lastName(entity.getLastName())
        .email(entity.getEmail())
        .affiliation(entity.getAffiliation())
        .build();
  }

  public static ReviewerEntity domainToEntity(Reviewer reviewer) {
    return ReviewerEntity.builder()
        .id(reviewer.getId() == null ? null : reviewer.getId())
        .firstName(reviewer.getFirstName())
        .lastName(reviewer.getLastName())
        .email(reviewer.getEmail())
        .affiliation(reviewer.getAffiliation())
        .build();
  }
}
