package com.solidos.caia.gateway.filters;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
  public static final List<String> openApiEndpoints = List.of(
      "/api/users/signup",
      "/api/users/confirm",
      "/api/users/login",
      "/api/users/health",
      "/api/conferences/health",
      "/api/authors/health",
      "/api/reviewers/health",
      "/api/conferences/public",
      "/api/authors/papers");

  public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
      .stream()
      .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
