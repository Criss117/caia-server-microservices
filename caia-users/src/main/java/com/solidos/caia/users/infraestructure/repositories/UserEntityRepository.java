package com.solidos.caia.users.infraestructure.repositories;

import com.solidos.caia.users.infraestructure.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  @Query("SELECT u.id FROM UserEntity u WHERE u.email = ?1")
  Optional<UserEntity> findIdByEmail(String email);

  Optional<UserEntity> findByToken(String token);
}
