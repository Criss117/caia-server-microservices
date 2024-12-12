package com.solidos.caia.users.application.ports.input;

import java.util.List;

import com.solidos.caia.users.domain.entities.User;

public interface UserService {
  public User signup(SignUpDto user);

  public void confirm(String token);

  public User findByEmail(String email);

  public Long findIdByEmail(String email);

  public AuthResponse login(LoginDto loginDto);

  public List<User> findByQuery(String query);
}