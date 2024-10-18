package com.solidos.caia.users.controllers;

import com.solidos.caia.users.dtos.AuthResponse;
import com.solidos.caia.users.dtos.LoginDto;
import com.solidos.caia.users.dtos.SignUpDto;
import com.solidos.caia.users.entites.UserEntity;
import com.solidos.caia.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("signup")
  public ResponseEntity<UserEntity> signup(@RequestBody @Validated SignUpDto user) {
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
    System.out.println("userEmail" + userEmail);

    return ResponseEntity.ok("This is a private endpoint");
  }

  @PostMapping("/login")
  public AuthResponse login(@RequestBody @Validated LoginDto loginDto) {

    return userService.login(loginDto);
  }
}
