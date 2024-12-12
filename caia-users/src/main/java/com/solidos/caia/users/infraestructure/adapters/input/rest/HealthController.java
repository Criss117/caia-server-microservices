package com.solidos.caia.users.infraestructure.adapters.input.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.users.utils.CommonResponse;

@RestController
@RequestMapping("health")
public class HealthController {

  @GetMapping
  public CommonResponse<String> health() {
    return CommonResponse.success("OK");
  }
}
