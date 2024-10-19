package com.solidos.caia.users.domain.repositories;

import com.solidos.caia.users.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
  User save(User user);
  Optional<User> findByEmail(String email);
  Long findIdByEmail(String email);
  Optional<User> findByToken(String token);
}
