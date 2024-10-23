package com.solidos.caia.authors.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.authors.entities.AuthorEntity;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

  @Query("SELECT a FROM AuthorEntity a WHERE a.email = ?1 AND a.auditMetadata.deletedAt IS NULL")
  Optional<AuthorEntity> findByEmail(String email);
}
