package com.solidos.caia.conferences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solidos.caia.conferences.entities.MemberComposeId;
import com.solidos.caia.conferences.entities.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, MemberComposeId> {

}
