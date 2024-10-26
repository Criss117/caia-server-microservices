package com.solidos.caia.users.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.solidos.caia.users.domain.entities.User;

public interface UserRepository {
  User save(User user);

  Optional<User> findByEmail(String email);

  Optional<User> findByEmail(String email, Boolean isEnabled);

  Long findIdByEmail(String email);

  User findByToken(String token);

  User findById(Long id);

  public List<User> findByQuery(String query);
}
