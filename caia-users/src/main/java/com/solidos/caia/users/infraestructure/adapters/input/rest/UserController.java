package com.solidos.caia.users.infraestructure.adapters.input.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.users.application.ports.input.AuthResponse;
import com.solidos.caia.users.application.ports.input.Example;
import com.solidos.caia.users.application.ports.input.LoginDto;
import com.solidos.caia.users.application.ports.input.SignUpDto;
import com.solidos.caia.users.application.ports.input.UserService;
import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.domain.service.RabbitMQProducer;
import com.solidos.caia.users.utils.CommonResponse;

@RestController
public class UserController {
  private final UserService userService;
  private final RabbitMQProducer rabbitMQProducer;

  public UserController(UserService userService, RabbitMQProducer rabbitMQProducer) {
    this.userService = userService;
    this.rabbitMQProducer = rabbitMQProducer;
  }

  @GetMapping("find-by-email")
  public ResponseEntity<CommonResponse<User>> findByEmail(@RequestParam String email) {
    User user = userService.findByEmail(email);

    user.setPassword(null);

    return ResponseEntity.ok(CommonResponse.success("User found", user));
  }

  @PostMapping("signup")
  public ResponseEntity<CommonResponse<Void>> signup(@RequestBody @Validated SignUpDto user) {
    userService.signup(user);
    return ResponseEntity.ok(CommonResponse.success("User created successfully"));
  }

  @PostMapping("confirm")
  public ResponseEntity<CommonResponse<String>> confirm(@RequestParam String token) {
    userService.confirm(token);

    return ResponseEntity.ok(CommonResponse.success("User confirmed  successfully"));
  }

  @PostMapping("login")
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

  @GetMapping("search")
  public ResponseEntity<CommonResponse<List<User>>> findByQuery(@RequestParam String query) {
    List<User> users = userService.findByQuery(query);
    return ResponseEntity.ok().body(CommonResponse.success("Query Success", users));
  }
}
