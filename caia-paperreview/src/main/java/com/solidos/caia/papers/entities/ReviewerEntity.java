package com.solidos.caia.paperreview.entities;

import com.solidos.caia.papers.utils.AuditMetadata;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviewers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
}
