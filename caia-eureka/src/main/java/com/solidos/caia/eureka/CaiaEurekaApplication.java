package com.solidos.caia.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CaiaEurekaApplication {

  public static void main(String[] args) {
    SpringApplication.run(CaiaEurekaApplication.class, args);
  }

}
