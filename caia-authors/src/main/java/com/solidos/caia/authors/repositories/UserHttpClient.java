package com.solidos.caia.authors.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.solidos.caia.authors.entities.AuthorEntity;
import com.solidos.caia.authors.utils.CommonResponse;

@FeignClient(name = "CAIA-USERS", path = "/api/users")
public interface UserHttpClient {
  @GetMapping("/{userEmail}")
  public ResponseEntity<CommonResponse<AuthorEntity>> findByEmail(@PathVariable String userEmail);
}
