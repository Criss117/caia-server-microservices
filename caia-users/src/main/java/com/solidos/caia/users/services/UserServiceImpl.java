package com.solidos.caia.users.services;

import com.solidos.caia.users.dtos.AuthResponse;
import com.solidos.caia.users.dtos.LoginDto;
import com.solidos.caia.users.dtos.SignUpDto;
import com.solidos.caia.users.entites.UserEntity;
import com.solidos.caia.users.repositories.UserRepository;
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
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtHelper jwtHelper;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtHelper = jwtHelper;
  }

  @Override
  @Transactional
  public UserEntity signup(SignUpDto user) {
    Optional<UserEntity> existsUser = userRepository.findByEmail(user.getEmail());

    if (existsUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    }

    UserEntity userEntity = UserEntity.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .affiliation(user.getAffiliation())
            .password(passwordEncoder.encode(user.getPassword()))
            .token(TokenGenerator.generate())
            .build();

    try {
      return userRepository.save(userEntity);
    } catch (Exception e) {
      throw new InternalException("Error creating user");
    }
  }

  @Override
  public void confirm(String token) {
    Optional<UserEntity> user = userRepository.findByToken(token);

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    UserEntity userEntity = user.get();

    if (userEntity.getIsEnabled()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already confirmed");
    }

    userEntity.setToken(null);
    userEntity.setAccountNoExpired(true);
    userEntity.setAccountNoLocked(true);
    userEntity.setCredentialsNoExpired(true);
    userEntity.setIsEnabled(true);

    try {
      userRepository.save(userEntity);
    } catch (Exception e) {
      throw new BadRequestException("Error confirming user");
    }
  }

  @Override
  public UserEntity findByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  @Override
  public Long findIdByEmail(String email) {
    return userRepository.findIdByEmail(email).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")).getId();
  }


  @Override
  public AuthResponse login(LoginDto loginDto) {
    UserEntity user = findByEmail(loginDto.getEmail());

    if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    return AuthResponse.builder()
            .email(user.getEmail())
            .jwt(jwtHelper.createToken(loginDto.getEmail()))
            .message("Login successful").status(true).build();
  }
}
