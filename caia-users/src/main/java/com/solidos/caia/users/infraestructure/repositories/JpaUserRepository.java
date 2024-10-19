package com.solidos.caia.users.infraestructure.repositories;

import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.domain.repositories.UserRepository;
import com.solidos.caia.users.infraestructure.adapters.UserAdapter;
import com.solidos.caia.users.infraestructure.entites.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class JpaUserRepository implements UserRepository {
  private final UserEntityRepository userEntityRepository;

  public JpaUserRepository(UserEntityRepository userEntityRepository) {
    this.userEntityRepository = userEntityRepository;
  }

  @Override
  public User save(User user) {
    UserEntity userEntity = UserAdapter.toEntity(user);

    UserEntity userSaved = userEntityRepository.save(userEntity);

    return UserAdapter.toDomain(userEntity);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    Optional<UserEntity> userEntity = userEntityRepository.findByEmail(email);

    return userEntity.map(UserAdapter::toDomain);
  }

  @Override
  public Long findIdByEmail(String email) {
    UserEntity userEntity = userEntityRepository.findIdByEmail(email).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    );

    return userEntity.getId();
  }

  @Override
  public Optional<User> findByToken(String token) {
    Optional<UserEntity> userEntity = userEntityRepository.findByToken(token);

    return userEntity.map(UserAdapter::toDomain);
  }
}
