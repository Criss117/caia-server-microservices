package com.solidos.caia.gateway.filters;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
  public static final List<String> openApiEndpoints = List.of(
          "/api/users/health",
          "/api/users/signup",
          "/api/users/confirm",
          "/api/users/login",
          "/api/conferences/health",
          "/api/authors"
  );

  public Predicate<ServerHttpRequest>  isSecured =
          request -> openApiEndpoints
                  .stream()
                  .noneMatch(uri -> request.getURI().getPath().contains(uri));


}
