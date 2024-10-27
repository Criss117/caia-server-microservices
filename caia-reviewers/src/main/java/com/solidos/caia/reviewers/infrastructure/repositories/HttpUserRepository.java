package com.solidos.caia.reviewers.infrastructure.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.solidos.caia.reviewers.domain.entities.Reviewer;
import com.solidos.caia.reviewers.utils.CommonResponse;

@FeignClient(name = "CAIA-USERS", path = "/api/users")
public interface HttpUserRepository {
  @GetMapping("/{userEmail}")
  public ResponseEntity<CommonResponse<Reviewer>> findByEmail(@PathVariable String userEmail);
}
