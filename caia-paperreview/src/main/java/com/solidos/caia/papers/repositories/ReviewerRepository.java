package com.solidos.caia.papers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solidos.caia.papers.entities.ReviewerEntity;

@Repository
public interface ReviewerRepository extends JpaRepository<AuthorEntity, Long> {

}
