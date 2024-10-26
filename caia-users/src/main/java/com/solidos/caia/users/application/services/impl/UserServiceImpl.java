package com.solidos.caia.users.application.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.users.application.adapters.UserAppAdapter;
import com.solidos.caia.users.application.dtos.AuthResponse;
import com.solidos.caia.users.application.dtos.LoginDto;
import com.solidos.caia.users.application.dtos.SignUpDto;
import com.solidos.caia.users.application.services.UserService;
import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.infraestructure.repositories.JpaUserRepository;
import com.solidos.caia.users.utils.JwtHelper;
import com.solidos.caia.users.utils.TokenGenerator;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

@Service
public class UserServiceImpl implements UserService {
  private final JpaUserRepository jpaUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtHelper jwtHelper;

  public UserServiceImpl(JpaUserRepository jpaUserRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
    this.jpaUserRepository = jpaUserRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtHelper = jwtHelper;
  }

  @Override
  @Transactional
  public User signup(SignUpDto user) {
    Optional<User> existsUser = jpaUserRepository.findByEmail(user.getEmail());

    if (!existsUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    }

    User newUser = UserAppAdapter.signupDtoToDomain(user);

    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    newUser.setToken(TokenGenerator.generate());

    try {
      return jpaUserRepository.save(newUser);
    } catch (Exception e) {
      throw new InternalException("Error creating user");
    }
  }

  @Override
  public void confirm(String token) {
    User user = jpaUserRepository.findByToken(token);

    user.confirmAccount();

    try {
      jpaUserRepository.save(user);
    } catch (Exception e) {
      throw new BadRequestException("Error confirming user");
    }
  }

  @Override
  public User findByEmail(String email) {
    return jpaUserRepository.findByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  @Override
  public Long findIdByEmail(String email) {
    return jpaUserRepository.findIdByEmail(email);
  }

  @Override
  public AuthResponse login(LoginDto loginDto) {
    User user = findByEmail(loginDto.getEmail());

    if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    return AuthResponse.builder()
        .email(user.getEmail())
        .jwt(jwtHelper.createToken(loginDto.getEmail()))
        .message("Login successful").status(true).build();
  }
  @Override
  public List<User> findByQuery(String query){
  return jpaUserRepository.findByQuery(query);
  }
}
