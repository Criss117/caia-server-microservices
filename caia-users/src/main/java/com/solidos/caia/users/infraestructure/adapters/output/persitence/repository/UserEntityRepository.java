package com.solidos.caia.users.infraestructure.adapters.output.persitence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.users.infraestructure.adapters.output.persitence.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  @Query("SELECT u FROM UserEntity u WHERE u.id = ?1 AND u.auditMetadata.isEnabled = true")
  Optional<UserEntity> findUserById(Long id);

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.auditMetadata.isEnabled = true")
  Optional<UserEntity> findByEmail(String email);

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.auditMetadata.isEnabled = ?2")
  Optional<UserEntity> findByEmail(String email, Boolean isEnabled);

  @Query("SELECT u FROM UserEntity u WHERE u.token = ?1 AND u.auditMetadata.isEnabled = true")
  Optional<UserEntity> findByToken(String token);

  @Query("SELECT u FROM UserEntity u WHERE u.token = ?1 AND u.auditMetadata.isEnabled = ?2")
  Optional<UserEntity> findByToken(String token, Boolean isEnabled);

  @Query("SELECT u FROM UserEntity u WHERE u.email LIKE %?1% OR u.firstName LIKE %?1% OR u.lastName LIKE %?1%")
  List<UserEntity> findByQuery(String query);
}
