package com.solidos.caia.users.application.services;

import com.solidos.caia.users.application.dtos.AuthResponse;
import com.solidos.caia.users.application.dtos.LoginDto;
import com.solidos.caia.users.application.dtos.SignUpDto;
import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.infraestructure.repositories.JpaUserRepository;
import com.solidos.caia.users.utils.JwtHelper;
import com.solidos.caia.users.utils.TokenGenerator;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private final JpaUserRepository jpaUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtHelper jwtHelper;

  public UserServiceImpl(JpaUserRepository jpaUserRepository,  PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
    this.jpaUserRepository = jpaUserRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtHelper = jwtHelper;
  }

  @Override
  @Transactional
  public User signup(SignUpDto user) {
    Optional<User> existsUser = jpaUserRepository.findByEmail(user.getEmail());

    if (existsUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    }

    User userEntity = User.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .affiliation(user.getAffiliation())
            .password(passwordEncoder.encode(user.getPassword()))
            .token(TokenGenerator.generate())
            .build();

    try {
      return jpaUserRepository.save(userEntity);
    } catch (Exception e) {
      throw new InternalException("Error creating user");
    }
  }

  @Override
  public void confirm(String token) {
    Optional<User> user = jpaUserRepository.findByToken(token);

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    User userEntity = user.get();

    if (userEntity.getIsEnabled()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already confirmed");
    }

    userEntity.setToken(null);
    userEntity.setAccountNoExpired(true);
    userEntity.setAccountNoLocked(true);
    userEntity.setCredentialsNoExpired(true);
    userEntity.setIsEnabled(true);

    try {
      jpaUserRepository.save(userEntity);
    } catch (Exception e) {
      throw new BadRequestException("Error confirming user");
    }
  }

  @Override
  public User findByEmail(String email) {
    return jpaUserRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
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
}
