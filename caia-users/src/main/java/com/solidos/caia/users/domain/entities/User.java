package com.solidos.caia.users.domain.entities;

import java.time.LocalDateTime;

import com.solidos.caia.users.utils.TokenGenerator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String affiliation;
  private String password;
  private String token;

  private AuditMetadata auditMetadata;

  public void confirmAccount() {
    this.token = null;
    this.auditMetadata.setIsEnabled(true);
  }

  public void prepareToFirstSave() {
    this.id = null;
    this.auditMetadata = AuditMetadata.builder()
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .isEnabled(false)
        .build();

    generateToken();
  }

  public void generateToken() {
    this.token = TokenGenerator.generate();
  }
}
