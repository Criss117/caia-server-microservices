package com.solidos.caia.conferences.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.solidos.caia.conferences.entities.User;
import com.solidos.caia.conferences.utils.CommonResponse;

@FeignClient(name = "CAIA-USERS", path = "/api/users")
public interface HttpUserRepository {
  @GetMapping("find-by-email")
  public ResponseEntity<CommonResponse<User>> findByEmail(@RequestParam String email);
}
