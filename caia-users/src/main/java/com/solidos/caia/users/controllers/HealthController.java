package com.solidos.caia.users.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/health")
public class HealthController {

  @GetMapping
  public String health() {
    return "OK";
  }
}
