package com.solidos.caia.authors.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solidos.caia.authors.enums.PaperStateEnum;
import com.solidos.caia.authors.utils.AuditMetadata;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "papers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaperEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "owner_id", updatable = false)
  @JsonBackReference
  private AuthorEntity authorEntity;

  @ManyToOne
  @JoinColumn(name = "conference_id", updatable = false)
  private ConferenceEntity conferenceEntity;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String fileName;

  @Column(nullable = false)
  private String keys;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private PaperStateEnum state = PaperStateEnum.WAITING_FOR_REVIEW;

  @Embedded
  private AuditMetadata auditMetadata;
}
