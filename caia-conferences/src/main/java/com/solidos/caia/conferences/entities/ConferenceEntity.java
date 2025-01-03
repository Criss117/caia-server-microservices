package com.solidos.caia.conferences.entities;

import com.solidos.caia.conferences.utils.AuditMetadata;

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
@Table(name = "conferences")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  // TODO: Add image upload functionality
  @Column(name = "banner_image_url")
  private String bannerImageUrl;

  @Column(nullable = false, unique = true)
  private String slug;

  @Embedded
  private AuditMetadata auditMetadata;
}
