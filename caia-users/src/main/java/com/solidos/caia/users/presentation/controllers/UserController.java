package com.solidos.caia.users.presentation.controllers;

import com.solidos.caia.users.application.dtos.AuthResponse;
import com.solidos.caia.users.application.dtos.Example;
import com.solidos.caia.users.application.dtos.LoginDto;
import com.solidos.caia.users.application.dtos.SignUpDto;
import com.solidos.caia.users.utils.CommonResponse;
import com.solidos.caia.users.application.services.RabbitMQProducer;
import com.solidos.caia.users.application.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
  private final UserService userService;
  private final RabbitMQProducer rabbitMQProducer;

  public UserController(UserService userService, RabbitMQProducer rabbitMQProducer) {
    this.userService = userService;
    this.rabbitMQProducer = rabbitMQProducer;
  }

  @PostMapping("signup")
  public ResponseEntity<CommonResponse<Void>> signup(@RequestBody @Validated SignUpDto user) {
    userService.signup(user);
    return ResponseEntity.ok(CommonResponse.success("User created successfully"));
  }

  /**
   * Confirms a user.
   *
   * @param token the user's confirmation token.
   * @return a response with a success message.
   */
  @PostMapping("confirm")
  public ResponseEntity<CommonResponse<String>> confirm(@RequestParam String token) {
    userService.confirm(token);

    return ResponseEntity.ok(CommonResponse.success("User confirmed  successfully"));
  }

  @GetMapping("private")
  public ResponseEntity<CommonResponse<String>> privateEndpoint(@RequestHeader("userEmail") String userEmail) {
    return ResponseEntity.ok(CommonResponse.success("This is a private endpoint"));
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponse<AuthResponse>> login(@RequestBody @Validated LoginDto loginDto) {
    return ResponseEntity.ok(
        CommonResponse.success(
            "Login successful",
            userService.login(loginDto)));
  }

  @PostMapping("rabbitmq")
  public ResponseEntity<CommonResponse<String>> rabbitmq(@RequestBody @Validated Example example) {
    rabbitMQProducer.sendMessage(example);
    return ResponseEntity.ok(CommonResponse.success("User confirmed  successfully"));
  }
}
