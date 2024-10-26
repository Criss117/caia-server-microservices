package com.solidos.caia.users.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.users.infraestructure.entites.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  @Query("SELECT u FROM UserEntity u WHERE u.id = ?1 AND u.isEnabled = true")
  Optional<UserEntity> findUserById(Long id);

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
  Optional<UserEntity> findByEmail(String email);

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.isEnabled = ?2")
  Optional<UserEntity> findByEmail(String email, Boolean isEnabled);

  @Query("SELECT u.id FROM UserEntity u WHERE u.email = ?1 AND u.isEnabled = true")
  Optional<UserEntity> findIdByEmail(String email);

  Optional<UserEntity> findByToken(String token);
  @Query("SELECT u FROM UserEntity u WHERE u.email LIKE %?1% OR u.firstName LIKE %?1% OR u.lastName LIKE %?1%")
  List<UserEntity> findByQuery(String query);
}
