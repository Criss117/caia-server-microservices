package com.solidos.caia.reviewers.domain.repositories;

import com.solidos.caia.reviewers.domain.entities.Paper;

public interface HttpPaperRepository {
  public Paper findPaper(Long id);
}
