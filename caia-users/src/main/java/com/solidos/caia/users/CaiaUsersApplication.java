package com.solidos.caia.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CaiaUsersApplication {

  public static void main(String[] args) {
    SpringApplication.run(CaiaUsersApplication.class, args);
  }

}
