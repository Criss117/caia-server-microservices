package com.solidos.caia.users.domain.service;

import java.util.List;

import org.apache.logging.log4j.util.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.users.application.adapters.UserAppAdapter;
import com.solidos.caia.users.application.ports.input.AuthResponse;
import com.solidos.caia.users.application.ports.input.LoginDto;
import com.solidos.caia.users.application.ports.input.SignUpDto;
import com.solidos.caia.users.application.ports.input.UserService;
import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.domain.repositories.UserRepository;
import com.solidos.caia.users.infraestructure.adapters.output.persitence.repository.UserRepositoryImpl;
import com.solidos.caia.users.utils.JwtHelper;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtHelper jwtHelper;

  public UserServiceImpl(UserRepositoryImpl userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtHelper = jwtHelper;
  }

  @Override
  @Transactional
  public User signup(SignUpDto user) {
    Boolean userExist = userRepository.userExist(user.getEmail());

    if (userExist) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    }

    User newUser = UserAppAdapter.signupDtoToDomain(user);

    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    newUser.prepareToFirstSave();

    try {
      return userRepository.save(newUser);
    } catch (Exception e) {
      throw new InternalException("Error creating user");
    }
  }

  @Override
  public void confirm(String token) {
    User user = userRepository.findByToken(token, false);

    user.confirmAccount();

    try {
      userRepository.save(user);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error confirming user");
    }
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Long findIdByEmail(String email) {
    return userRepository.findIdByEmail(email);
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
  public List<User> findByQuery(String query) {
    return userRepository.findByQuery(query);
  }
}
