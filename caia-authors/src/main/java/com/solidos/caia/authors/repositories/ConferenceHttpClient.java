package com.solidos.caia.authors.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.solidos.caia.authors.entities.ConferenceEntity;
import com.solidos.caia.authors.utils.CommonResponse;

@FeignClient(name = "CAIA-CONFERENCES", path = "/api/conferences")
public interface ConferenceHttpClient {
  @GetMapping("/public/{slug}")
  public ResponseEntity<CommonResponse<ConferenceEntity>> publicFindConference(@PathVariable String slug);
}
