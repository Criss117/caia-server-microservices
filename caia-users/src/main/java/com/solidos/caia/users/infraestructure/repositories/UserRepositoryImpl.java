package com.solidos.caia.users.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.domain.repositories.UserRepository;
import com.solidos.caia.users.infraestructure.entites.UserEntity;
import com.solidos.caia.users.infraestructure.mappers.UserMapper;

import jakarta.transaction.Transactional;

@Component
public class UserRepositoryImpl implements UserRepository {
  private final UserEntityRepository userEntityRepository;

  public UserRepositoryImpl(UserEntityRepository userEntityRepository) {
    this.userEntityRepository = userEntityRepository;
  }

  @Override
  @Transactional
  public User save(User user) {
    UserEntity userEntity = UserMapper.toEntity(user);

    UserEntity userSaved = userEntityRepository.save(userEntity);

    return UserMapper.toDomain(userSaved);
  }

  @Override
  public User findByEmail(String email) {
    UserEntity userEntity = userEntityRepository.findByEmail(email).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return UserMapper.toDomain(userEntity);
  }

  @Override
  public User findByEmail(String email, Boolean isEnabled) {
    UserEntity userEntity = userEntityRepository.findByEmail(email, isEnabled).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return UserMapper.toDomain(userEntity);
  }

  @Override
  public Long findIdByEmail(String email) {
    UserEntity userEntity = userEntityRepository.findByEmail(email).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return userEntity.getId();
  }

  @Override
  public User findByToken(String token) {
    UserEntity userEntity = userEntityRepository.findByToken(token).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return UserMapper.toDomain(userEntity);
  }

  @Override
  public User findByToken(String token, Boolean isEnabled) {
    UserEntity userEntity = userEntityRepository.findByToken(token, isEnabled).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return UserMapper.toDomain(userEntity);
  }

  @Override
  public User findById(Long id) {
    UserEntity userEntity = userEntityRepository.findUserById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return UserMapper.toDomain(userEntity);
  }

  @Override
  public List<User> findByQuery(String query) {
    List<UserEntity> userEntity = userEntityRepository.findByQuery(query);

    return userEntity.stream().map(UserQ -> UserMapper.toDomain(UserQ)).toList();
  }

  @Override
  public Boolean userExist(String email) {
    Optional<UserEntity> userEntity = userEntityRepository.findByEmail(email);

    return userEntity.isPresent();
  }
}
