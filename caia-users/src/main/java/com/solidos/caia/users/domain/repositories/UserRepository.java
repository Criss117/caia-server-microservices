package com.solidos.caia.users.domain.repositories;

import java.util.List;

import com.solidos.caia.users.domain.entities.User;

public interface UserRepository {
  User save(User user);

  Boolean userExist(String email);

  User findByEmail(String email);

  User findByEmail(String email, Boolean isEnabled);

  Long findIdByEmail(String email);

  User findByToken(String token);

  User findByToken(String token, Boolean isEnabled);

  User findById(Long id);

  List<User> findByQuery(String query);
}
