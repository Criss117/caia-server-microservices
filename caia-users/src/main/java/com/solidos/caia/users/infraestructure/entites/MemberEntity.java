package com.solidos.caia.users.infraestructure.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberEntity {
  @EmbeddedId
  private MemberComposeId memberComposeId;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private UserEntity userEntity;

  @ManyToOne
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  @JsonBackReference
  private RoleEntity roleEntity;

  @Embedded
  private EntityAuditMetadata auditMetadata;
}
