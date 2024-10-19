package com.solidos.caia.users.presentation.controllers;

import com.solidos.caia.users.application.dtos.AuthResponse;
import com.solidos.caia.users.application.dtos.Example;
import com.solidos.caia.users.application.dtos.LoginDto;
import com.solidos.caia.users.application.dtos.SignUpDto;
import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.application.services.RabbitMQProducer;
import com.solidos.caia.users.application.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserService userService;
  private final RabbitMQProducer rabbitMQProducer;

  public UserController(UserService userService, RabbitMQProducer rabbitMQProducer) {
    this.userService = userService;
    this.rabbitMQProducer = rabbitMQProducer;
  }

  @PostMapping("signup")
  public ResponseEntity<User> signup(@RequestBody @Validated SignUpDto user) {
    return ResponseEntity.ok(userService.signup(user));
  }

  /**
   * Confirms a user.
   *
   * @param token the user's confirmation token.
   * @return a response with a success message.
   */
  @PostMapping("confirm")
  public ResponseEntity<String> confirm(@RequestParam String token) {
    try {
      userService.confirm(token);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    return ResponseEntity.ok("User confirmed  successfully");
  }

  @GetMapping("private")
  public ResponseEntity<String> privateEndpoint(@RequestHeader("userEmail") String userEmail) {
    return ResponseEntity.ok("This is a private endpoint");
  }

  @PostMapping("/login")
  public AuthResponse login(@RequestBody @Validated LoginDto loginDto) {
    return userService.login(loginDto);
  }

  @PostMapping("rabbitmq")
  public ResponseEntity<String> rabbitmq(@RequestBody @Validated Example example) {
    rabbitMQProducer.sendMessage(example);
    return ResponseEntity.ok("User confirmed  successfully");
  }
}
