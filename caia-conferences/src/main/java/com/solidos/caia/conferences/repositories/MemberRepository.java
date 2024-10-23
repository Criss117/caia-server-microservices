package com.solidos.caia.conferences.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solidos.caia.conferences.entities.MemberComposeId;
import com.solidos.caia.conferences.entities.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, MemberComposeId> {
  @Query("SELECT m FROM MemberEntity m WHERE m.memberComposeId.organizerId = ?1")
  List<MemberEntity> findConferencesIdByConferencesId(Long conferencesId);

  @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM MemberEntity m" +
      " WHERE m.memberComposeId.organizerId = ?1 AND m.memberComposeId.conferenceId = ?2")
  Boolean isOrganizer(Long organizerId, Long conferenceId);
}
