package com.solidos.caia.papers.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.solidos.caia.papers.entities.AuthorEntity;
import com.solidos.caia.papers.utils.CommonResponse;

@FeignClient(name = "CAIA-USERS", path = "/api/users")
public interface HttpUserRepository {
  @GetMapping("/{userEmail}")
  public ResponseEntity<CommonResponse<AuthorEntity>> findByEmail(@PathVariable String userEmail);
}
