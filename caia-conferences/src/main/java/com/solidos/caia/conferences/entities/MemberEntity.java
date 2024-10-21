package com.solidos.caia.conferences.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solidos.caia.conferences.utils.AuditMetadata;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {
  @EmbeddedId
  private MemberComposeId memberComposeId;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  // @JsonBackReference
  private OrganizerEntity userEntity;

  @ManyToOne
  @JoinColumn(name = "conference_id", insertable = false, updatable = false)
  @JsonBackReference
  private ConferenceEntity conferenceEntity;

  @Embedded
  private AuditMetadata auditMetadata;

}
