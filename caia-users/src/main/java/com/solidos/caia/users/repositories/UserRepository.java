package com.solidos.caia.users.repositories;

import com.solidos.caia.users.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  @Query("SELECT u.id FROM UserEntity u WHERE u.email = ?1")
  Optional<UserEntity> findIdByEmail(String email);

  Optional<UserEntity> findByToken(String token);
}
