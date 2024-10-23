package com.solidos.caia.authors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solidos.caia.authors.entities.PaperEntity;

@Repository
public interface PaperRepository extends JpaRepository<PaperEntity, Long> {

}
