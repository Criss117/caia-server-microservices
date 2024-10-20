package com.solidos.caia.users.domain.repositories;

import com.solidos.caia.users.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
  User save(User user);

  Optional<User> findByEmail(String email);

  Optional<User> findByEmail(String email, Boolean isEnabled);

  Long findIdByEmail(String email);

  User findByToken(String token);
}
