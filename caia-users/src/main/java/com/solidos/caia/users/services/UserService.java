package com.solidos.caia.users.services;

import com.solidos.caia.users.dtos.AuthResponse;
import com.solidos.caia.users.dtos.LoginDto;
import com.solidos.caia.users.dtos.SignUpDto;
import com.solidos.caia.users.entites.UserEntity;

public interface UserService {
  public UserEntity signup(SignUpDto user);
  public void confirm(String token);
  public UserEntity findByEmail(String email);
  public Long findIdByEmail(String email);
  public AuthResponse login(LoginDto loginDto);
}
