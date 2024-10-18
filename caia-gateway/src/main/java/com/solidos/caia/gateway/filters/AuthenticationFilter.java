package com.solidos.caia.gateway.filters;

import com.solidos.caia.gateway.utils.JwtHelper;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
  private final RouteValidator routeValidator;
  private final JwtHelper jwtHelper;

  public  AuthenticationFilter( RouteValidator routeValidator, JwtHelper jwtHelper) {
    super(Config.class);
    this.routeValidator = routeValidator;
    this.jwtHelper = jwtHelper;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (((exchange, chain) -> {
      ServerHttpRequest request = null;

      if (routeValidator.isSecured.test(exchange.getRequest())) {
        if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
          throw new RuntimeException("Authorization Header Missing");
        }

        String authheaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        if(authheaders == null) {
          throw new RuntimeException("Authorization Header Missing");
        }

        if(!authheaders.startsWith("Bearer ")) {
          throw new RuntimeException("Authorization Header Invalid");
        }

        authheaders = authheaders.replace("Bearer ", "");

        try{
          jwtHelper.validateToken(authheaders);

          request = exchange.getRequest()
                  .mutate()
                  .header("useremail", jwtHelper.extractEmail(jwtHelper.validateToken(authheaders)))
                  .build();
          
        }catch ( Exception e) {
          throw new RuntimeException("Authorization Header Invalid");
        }

      }

      return chain.filter(exchange.mutate().request(request).build());
    }));
  }

  public static class Config  {

  }
}
